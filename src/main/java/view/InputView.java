package view;

import dto.NamesDTO;
import dto.PinDTO;
import dto.NameDTO;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private <T> T tryCatch(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력할 수 있습니다.");
            return tryCatch(supplier);
        } catch (IllegalArgumentException e) {
            System.out.printf("%s\n", e.getMessage());
            return tryCatch(supplier);
        }
    }

    public NameDTO inputName(int index) {
        return tryCatch(() -> {
            System.out.printf("플레이어 %2d의 이름은? ( 3 english letters ) : ", index);
            String name = scanner.nextLine();
            return NameDTO.of(name);
        });
    }

    public PinDTO inputPin(NameDTO nameDTO) {
        return tryCatch(() -> {
            System.out.printf("\n%s's turn : ", nameDTO.getName());
            int pin = Integer.parseInt(scanner.nextLine());
            return PinDTO.of(pin);
        });
    }

    public int inputPersonnel() {
        return tryCatch(() -> {
            System.out.print("How many people? ");
            return Integer.parseInt(scanner.nextLine());
        });
    }

    public NamesDTO inputNames(int personnel) {
        List<NameDTO> nameDTOs = tryCatch(() -> IntStream.rangeClosed(1, personnel)
                .mapToObj(this::inputName)
                .collect(Collectors.toList()));
        return NamesDTO.of(nameDTOs);
    }
}
