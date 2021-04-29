package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String REQUEST_NUMBER_OF_PEOPLE = "How many people? ";
    private static final String REQUEST_PLAYER_NAME = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String WHOSE_TURN = "%s's turn : ";

    private InputView() {}

    public static String requestNumberOfPeople() {
        System.out.print(REQUEST_NUMBER_OF_PEOPLE);
        return SCANNER.nextLine();
    }

    public static String requestPlayerName() {
        System.out.print(REQUEST_PLAYER_NAME);
        return SCANNER.nextLine();
    }

    public static int requestFallingPins(String playerName) {
        System.out.printf(WHOSE_TURN, playerName);
        return Integer.parseInt(SCANNER.nextLine());
    }
}
