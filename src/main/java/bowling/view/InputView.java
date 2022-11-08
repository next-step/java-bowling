package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NAME_QUESTION = "플레이어 이름은(3 english letters)?: ";
    private static final String FALLEN_PINS_QUESTION_FORMAT = "%s프레임 투구 : ";

    public static void closeScan() {
        SCANNER.close();
    }

    public static String scanName() {
        System.out.print(NAME_QUESTION);
        return SCANNER.nextLine();
    }

    public static int scanFallenPinCount(int frameNumber) {
        System.out.printf(FALLEN_PINS_QUESTION_FORMAT, frameNumber);
        return Integer.parseInt(SCANNER.nextLine());
    }
}
