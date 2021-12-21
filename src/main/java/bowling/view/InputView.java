package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_NAME_MESSAGE = "플레이어 이름은(3 english letters) ? : ";
    private static final String FALLEN_PIN = "%d프레임 투구 : ";
    private static final String NUMBER_TYPE_ERROR_MESSAGE = "error : 숫자만 입력 가능합니다.";

    private static final Scanner scanner = new Scanner(System.in);

    public static String InputPlayerName() {
        System.out.print(INPUT_NAME_MESSAGE);
        return scanner.nextLine();
    }

    public static int InputFallenPin(int frame) {
        System.out.printf(FALLEN_PIN, frame);
        return InputNumber();
    }

    private static int InputNumber() {
        if (!scanner.hasNextInt()) {
            throw new IllegalArgumentException(NUMBER_TYPE_ERROR_MESSAGE);
        }

        int number = scanner.nextInt();
        scanner.nextLine();

        return number;
    }

}
