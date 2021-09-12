package bowling.view;

import java.io.PrintStream;
import java.util.Scanner;

public class InputView {
    private static final String PLAYER_NAME_INPUT_MESSAGE = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String FALLEN_PIN_COUNT_INPUT_MESSAGE = "%s's turn : ";
    private static final String PLAYER_COUNT_INPUT_MESSAGE = "How many people? ";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final PrintStream PRINT_STREAM = System.out;

    public static String inputPlayerName(int playerNumber) {
        PRINT_STREAM.print(String.format(PLAYER_NAME_INPUT_MESSAGE, playerNumber));
        return SCANNER.nextLine();
    }

    public static int inputFallenPinCount(String playerName) {
        PRINT_STREAM.print(String.format(FALLEN_PIN_COUNT_INPUT_MESSAGE, playerName));
        return SCANNER.nextInt();
    }

    public static int inputPlayerCount() {
        PRINT_STREAM.print(PLAYER_COUNT_INPUT_MESSAGE);
        return Integer.parseInt(SCANNER.nextLine());
    }
}
