package bowling.view;

import bowling.domain.player.Player;

import java.util.Scanner;

public class InputView {
    private static final String GET_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String GET_ROLLING_PIN_COUNT_MESSAGE = "%d프레임 투구 : ";

    private static final Scanner scanner = new Scanner(System.in);

    public static String getPlayer() {
        String name = null;

        do {
            name = getString(GET_PLAYER_NAME_MESSAGE);
        } while (!InputValidChecker.isNameValid(name));

        return name;
    }

    private static String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public static int getRollingCount(int frameCount) {
        int knockedDownPinCount;

        do {
            knockedDownPinCount = getInt(String.format(GET_ROLLING_PIN_COUNT_MESSAGE, frameCount));
        } while (!InputValidChecker.isPinCountValid(knockedDownPinCount));

        return knockedDownPinCount;
    }

    private static int getInt(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }
}
