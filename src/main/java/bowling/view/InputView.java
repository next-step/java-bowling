package bowling.view;

import bowling.util.ScannerUtil;

import static bowling.Messages.MESSAGE_INPUT_NAME;

public class InputView {
    private static final String DELIMITER = " : ";

    public static String getPlayerName() {
        printMessage(MESSAGE_INPUT_NAME + DELIMITER);
        return ScannerUtil.readLine();
    }

    private static void printMessage(String message){
        System.out.println(message);
    }
}