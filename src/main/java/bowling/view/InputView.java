package bowling.view;

import bowling.domain.Pitching;
import bowling.domain.Player;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final String PLAYER_NUMBER_PROMPT = "How many people? ";
    private static final String PLAYER_NAME_PROMPT = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String INPUT_FALLEN_PINS_MESSAGE = "%s's turn : ";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<Player> inputPlayer() {
        int playerNumber = inputPlayerNumber();

        return inputNamesForNumberOfPlayers(playerNumber);
    }

    private static int inputPlayerNumber() {
        System.out.println(PLAYER_NUMBER_PROMPT);
        return toInt(SCANNER.nextLine());
    }

    private static List<Player> inputNamesForNumberOfPlayers(int playerNumber) {
        return IntStream.rangeClosed(1, playerNumber)
                .mapToObj(InputView::inputPlayerName)
                .collect(Collectors.toList());
    }

    private static Player inputPlayerName(int index) {
        System.out.println(String.format(PLAYER_NAME_PROMPT, index));
        return new Player(SCANNER.nextLine());
    }

    public static Pitching inputFallenPins(String currentPlayerName) {
        System.out.printf(INPUT_FALLEN_PINS_MESSAGE, currentPlayerName);
        return new Pitching(toInt(SCANNER.nextLine()));
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
