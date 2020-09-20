package view;

import dto.PinDTO;
import dto.NameDTO;

import java.util.Scanner;
import java.util.function.Supplier;

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

    public NameDTO inputName() {
        return tryCatch(() -> {
            System.out.print("플레이어 이름은? ( 3 english letters ) : ");
            String name = scanner.nextLine();
            return NameDTO.of(name);
        });
    }

    public PinDTO inputPin(NameDTO nameDTO) {
        return tryCatch(() -> {
            System.out.printf("\n%s' turn : ", nameDTO.getName());
            int pin = Integer.parseInt(scanner.nextLine());
            return PinDTO.of(pin);
        });
    }
}
