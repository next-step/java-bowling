package bowling.view;

import bowling.domain.Player;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final String INPUT_PLAYER_NUMBER = "How many people? ";
    private static final String INPUT_PLAYER_NAME = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String INPUT_KNOCK_DOWN_PINS = "%s's turn : ";

    private static Scanner scanner = new Scanner(System.in);

    public static int inputPlayerNumber() {
        System.out.print(INPUT_PLAYER_NUMBER);
        int playerNumber = scanner.nextInt();
        return playerNumber;
    }

    public static String inputPlayerName(int playerIndex) {
        System.out.format(INPUT_PLAYER_NAME, (playerIndex + 1));
        String playerName = scanner.next();

        return playerName;
    }

    public static int inputKnockedDownPins(Player player) {
        System.out.println();
        System.out.format(INPUT_KNOCK_DOWN_PINS, player.getName());
        return scanner.nextInt();
    }

    public static String[] inputPlayerNames(int playerNumber) {

        String[] playerNames = IntStream.range(0, playerNumber)
                .mapToObj(InputView::inputPlayerName)
                .toArray(String[]::new);

        return playerNames;

    }
}
