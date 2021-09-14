package bowling.view;

import bowling.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String GET_PLAYER_NAMES_PREFIX = "플레이어 이름 ";
    private static final String GET_PLAYER_NAMES_SUFFIX = "의 이름은(3 english letters)?: ";

    private static final String GET_FRAME_SCORE = "'s turn : ";
    private static final String GET_PLAYER_COUNT = "How many people? ";

    public static List<Player> getPlayerNames(int count) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            System.out.print(GET_PLAYER_NAMES_PREFIX + i + GET_PLAYER_NAMES_SUFFIX);
            String name = scanner.next();
            players.add(Player.from(name));
        }

        return players;
    }

    public static int getPlayerCount() {
        System.out.print(GET_PLAYER_COUNT);
        int count = scanner.nextInt();
        return count;
    }

    public static int getFrameScore(Player player) {
        System.out.print(player.name() + GET_FRAME_SCORE);
        int score = scanner.nextInt();
        return score;
    }
}
