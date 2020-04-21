package bowling.view;

import bowling.util.ScannerUtil;

import static bowling.Messages.MESSAGE_INPUT_PLAYER_COUNT;

public class InputView {
    public static int getPlayerCount() {
        printMessage(MESSAGE_INPUT_PLAYER_COUNT);
        return ScannerUtil.readInt();
    }

    public static String getPlayerName(int playerCount) {
        printInputMessage(playerCount);
        return ScannerUtil.readLine();
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    private static void printInputMessage(int playerCount) {
        System.out.println("플레이어 " + playerCount + "의 이름은? (3 English letter) : ");
    }
}