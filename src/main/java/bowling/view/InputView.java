package bowling.view;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final String INPUT_PLAYER_NUMBER = "How many people? ";
    private static final String INPUT_PLAYER_NAME = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String INPUT_KNOCK_DOWN_PINS = "프레임 투구 : ";

    private static Scanner scanner = new Scanner(System.in);

    public static int inputPlayerNumber() {
        System.out.print(INPUT_PLAYER_NUMBER);
        int playerNumber = scanner.nextInt();
        return playerNumber;
    }

    public static String inputPlayerName(int playerIndex) {
        /*System.out.print(INPUT_PLAYER_NAME);
        String playerName = scanner.next();*/
        System.out.format(INPUT_PLAYER_NAME, (playerIndex + 1));
        String playerName = scanner.next();

        return playerName;
    }

    public static int inputKnockedDownPins(int frameIndex) {
        System.out.println();
        System.out.print(frameIndex + INPUT_KNOCK_DOWN_PINS);
        return scanner.nextInt();
    }

    public static String[] inputPlayerNames(int playerNumber) {

        String[] playerNames = IntStream.range(0, playerNumber)
                .mapToObj(InputView::inputPlayerName)
                .toArray(String[]::new);

        return playerNames;

    }
}
