package bowling.view;

import bowling.domain.player.PlayerCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {

    private static final String PLAYER_NAMES_MESSAGE = "-> 플레이어 %d번의 이름은(3 english letters)? ";
    private static final String WHO_IS_TURN = "%s's turn : ";
    private static final String HOW_MANY_PEOPLE = "볼링 게임을 할 플레이어 수는? ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputPlayers() {
        final PlayerCount playerCount = PlayerCount.of(inputPlayerCount());

        List<String> playerNames = new ArrayList<>();
        IntStream.rangeClosed(1, playerCount.getCount())
                .forEach(index -> playerNames.add(inputPlayer(index)));

        return playerNames;
    }

    private static int inputPlayerCount() {
        return inputInt(HOW_MANY_PEOPLE);
    }

    private static String inputPlayer(final int index) {
        return inputString(String.format(PLAYER_NAMES_MESSAGE, index));
    }

    public static int inputHitCount(final String playerName) {
        return inputInt(String.format(WHO_IS_TURN, playerName));
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
