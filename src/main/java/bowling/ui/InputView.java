package bowling.ui;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INSERT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String WHOSE_TURN = "%s's turn : ";
    private static final String HOW_MANY_PEOPLE = "how many people? ";

    private InputView() {}

    public static String askPlayerName() {
        System.out.print(INSERT_PLAYER_NAME);
        return SCANNER.nextLine();
    }

    public static int askNumberOfPins(String name) {
        System.out.printf(WHOSE_TURN, name);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static int askNumberOfPeople() {
        System.out.print(HOW_MANY_PEOPLE);
        return Integer.parseInt(SCANNER.nextLine());
    }
}