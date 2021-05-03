package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String GET_PLAYER_NAME_MESSAGE = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String GET_TOPPLE_PIN_MESSAGE = "%d프레임 투구: ";
    private static final String GET_PLAYER_COUNT_MESSAGE = "How many people? ";

    private InputView() {
    }

    public static String getPlayerName(int count) {
        System.out.printf(GET_PLAYER_NAME_MESSAGE, count);
        return SCANNER.nextLine();
    }

    public static int getTopplePin(int round) {
        System.out.printf(GET_TOPPLE_PIN_MESSAGE, round);
        return SCANNER.nextInt();
    }

    public static int getPlayerCount() {
        System.out.print(GET_PLAYER_COUNT_MESSAGE);
        return SCANNER.nextInt();
    }
}
