package bowling.view;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.score.Pins;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Created : 2020-12-16 오전 9:23
 * Developer : Seo
 */
public class InputView {
    public static final String ENTER_PLAYERS = "How many people? ";
    public static final String ENTER_PLAYER = "플레이어 %d의 이름은?(3 english letters): ";
    public static final String S = "'s ";
    public static final String ENTER_PINS = " turn : ";

    private static final Scanner scanner = new Scanner(System.in);
    public static final int INDEX_ONE = 1;

    public static Players getPlayers() {
        print(ENTER_PLAYERS);

        int playersCount = scanner.nextInt();
        Players players = new Players();
        IntStream.rangeClosed(INDEX_ONE, playersCount).forEach(value -> {
            printf(ENTER_PLAYER, value);
            players.add(new Player(scanner.next()));
        });
        return players;
    }

    public static Pins getPins(Player player, int frameNo) {
        print(player.getName() + S + (frameNo + 1) + ENTER_PINS);
        return new Pins(scanner.nextInt());
    }

    private static void print(String args) {
        System.out.print(args);
    }

    private static void printf(String format, int args) {
        System.out.printf(format, args);
    }

    private InputView() {
    }
}
