package bowling.view;

import bowling.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
    private static final String INPUT_PLAYER_COUNT_MESSAGE = "How many people? ";
    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 %d의 이름은?(3 english letters): ";
    private static final String INPUT_BOWLING_COUNT_FRAME_MESSAGE = "'s turn : ";

    public static int inputPlayerCount() {
        System.out.print(INPUT_PLAYER_COUNT_MESSAGE);
        final int playerCount = scanner.nextInt();

        scanner.nextLine();
        return playerCount;
    }

    public static List<Player> inputPlayerNames(final int playerCount) {
        List<String> playerNames = new ArrayList<>();

        for (int count = 1; count <= playerCount; count++) {
            System.out.println(String.format(INPUT_PLAYER_NAME_MESSAGE, count));
            final String name = scanner.nextLine();

            playerNames.add(name);
        }

        return playerNames.stream()
                .map(Player::of)
                .collect(Collectors.toList());
    }

    public static int inputDropPinCount(final Player player) {
        System.out.print(player.getName() + INPUT_BOWLING_COUNT_FRAME_MESSAGE);
        final int pinCount = scanner.nextInt();

        scanner.nextLine();
        return pinCount;
    }
}
