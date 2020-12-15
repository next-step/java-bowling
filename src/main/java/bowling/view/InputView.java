package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String INPUT_USER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_SCORE_MESSAGE = "프레임 투구 : ";

    private InputView() {
    }

    public static String printInputUserNameMessage() {
        System.out.print(INPUT_USER_NAME_MESSAGE);
        return getStringFromUserInput();
    }

    public static int printInputFallenPinsMessage(int frameNumber) {
        System.out.print(frameNumber + INPUT_SCORE_MESSAGE);
        return getIntFromUserInput();
    }

    private static String getStringFromUserInput() {
        return SCANNER.nextLine();
    }

    private static int getIntFromUserInput() {
        return SCANNER.nextInt();
    }
}
