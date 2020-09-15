package view;

import dto.DTO;
import dto.FalledPinsDTO;
import dto.NameDTO;

import java.util.Scanner;
import java.util.function.Supplier;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private DTO tryCatch(Supplier<DTO> supplier) {
        try {
            return supplier.get();
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력할 수 있습니다.\n");
            return tryCatch(supplier);
        } catch (IllegalArgumentException e) {
            System.out.printf("%s\n\n", e.getMessage());
            return tryCatch(supplier);
        }
    }

    public NameDTO inputName() {
        return (NameDTO) tryCatch(() -> {
            System.out.print("플레이어 이름은? ( 3 english letters ) : ");
            String name = scanner.nextLine();
            return NameDTO.of(name);
        });
    }

    public FalledPinsDTO inputFalledPins(int frame) {
        return (FalledPinsDTO) tryCatch(() -> {
            System.out.printf("%d 프레임 투구 : ", frame);
            int falledPins = Integer.parseInt(scanner.nextLine());
            return FalledPinsDTO.of(falledPins);
        });
    }
}
