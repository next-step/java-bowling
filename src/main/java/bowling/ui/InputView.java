package bowling.ui;

import bowling.domain.game.BowlingGames;
import bowling.domain.player.Player;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final String INPUT_SHOULD_INTEGER = "자연수로 입력 해 주세요.";
    private static final String PLEASE_INPUT_PLAYER_NAME = "플레이어 %s의 이름은?(3 english letters): ";
    private static final String PLEASE_INPUT_N_FRAME_PITCH_RESULT = "%s's turn :";
    private static final String PLEASE_INPUT_PLAYER_COUNT = "How many people? ";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static BowlingGames prepareGames() {
        int playerCount = getPlayerCount();
        List<Player> players = IntStream.rangeClosed(1, playerCount)
            .mapToObj(InputView::getPlayer)
            .collect(Collectors.toList());
        return new BowlingGames(players);
    }

    public static int getPlayerCount() {
        System.out.print(PLEASE_INPUT_PLAYER_COUNT);
        return getIntValue();
    }

    public static Player getPlayer(int index) {
        System.out.print(String.format(PLEASE_INPUT_PLAYER_NAME, index));
        return Player.create(getStringValue());
    }

    public static int getCurrentFramePitch(String playerName) {
        System.out.printf(PLEASE_INPUT_N_FRAME_PITCH_RESULT, playerName);
        return getIntValue();
    }

    private static int getIntValue() {
        try {
            return SCANNER.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException(INPUT_SHOULD_INTEGER, e);
        } finally {
            SCANNER.nextLine();
        }
    }

    private static String getStringValue() {
        return SCANNER.nextLine();
    }
}
