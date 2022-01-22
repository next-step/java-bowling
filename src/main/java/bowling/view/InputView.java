package bowling.view;

import bowling.Pins;
import bowling.Player;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_NUMBER_OF_PLAYER = "How many people? : ";
    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)? : ";
    private static final String INPUT_FALLEN_PINS_MESSAGE = "%s's turn : ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static int inputNumberOfPlayer() {
        System.out.print(INPUT_NUMBER_OF_PLAYER);
        return SCANNER.nextInt();
    }

    public static Player inputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME_MESSAGE);
        return new Player(SCANNER.next());
    }

    public static Pins inputFallenPins(String playerName) {
        System.out.printf(INPUT_FALLEN_PINS_MESSAGE, playerName);
        return new Pins(SCANNER.nextInt());
    }
}
