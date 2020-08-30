package bowling.view;

import bowling.domian.player.Player;
import bowling.domian.player.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String GET_PLAYER_COUNT_MESSAGE = "How many people? ";
    private static final String GET_PLAYER_NAME_MESSAGE = "플레이어 %s의 이름은?(3 english letters): ";
    private static final String GET_ROLLING_PIN_COUNT_MESSAGE = "%s's turn : ";

    private static final Scanner scanner = new Scanner(System.in);

    public static Players getPlayers() {
        int playersNum = getPlayersNumber();
        List<Player> players = new ArrayList<>();

        for (int i = 1; i <= playersNum; i++) {
            players.add(getPlayer(i));
        }

        return Players.of(players);
    }

    private static int getPlayersNumber() {
        System.out.println(GET_PLAYER_COUNT_MESSAGE);

        return Integer.parseInt(scanner.nextLine());
    }

    private static Player getPlayer(int index) {
        System.out.println(String.format(GET_PLAYER_NAME_MESSAGE, index));

        String name = scanner.nextLine();
        return Player.get(name);
    }

    public static int getFalledPinsCount(int frameCount) {
        System.out.println(String.format(GET_ROLLING_PIN_COUNT_MESSAGE, frameCount));

        return scanner.nextInt();
    }
}
