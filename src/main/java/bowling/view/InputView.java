package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String PLAYER_NAMES_MESSAGE = "플레이어 이름은(3 english letters)? ";
    private static final String WHO_IS_TURN = "%s's turn : ";
    private static final String HOW_MANY_PEOPLE = "How many people? ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayer() {
        return inputString(PLAYER_NAMES_MESSAGE);
    }

    public static int inputHitCount(final int playerName) {
        return inputInt(String.format(WHO_IS_TURN, playerName));
    }

    public static int inputPlayerCount() {
        return inputInt(HOW_MANY_PEOPLE);
    }

    private static String inputString(final String message) {
        System.out.println();
        System.out.print(message);

        return SCANNER.nextLine();
    }

    private static int inputInt(final String message) {
        return Integer.parseInt(inputString(message));
    }
}
