package bowling.view;

import bowling.util.ScannerUtil;

import static bowling.Messages.MESSAGE_INPUT_FIRST_PLAYER_NAME;
import static bowling.Messages.MESSAGE_INPUT_SECOND_PLAYER_NAME;

public class InputView {
    private static final String DELIMITER = " : ";

    public static String getFirstPlayerName() {
        printMessage(MESSAGE_INPUT_FIRST_PLAYER_NAME + DELIMITER);
        return ScannerUtil.readLine();
    }

    public static String getSecondPlayerName() {
        printMessage(MESSAGE_INPUT_SECOND_PLAYER_NAME + DELIMITER);
        return ScannerUtil.readLine();
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }
}