package bowling.view;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.score.Pins;
import bowling.domain.state.State;

import java.util.Scanner;

/**
 * Created : 2020-12-16 오전 9:23
 * Developer : Seo
 */
public class InputView {
    public static final String ENTER_PLAYERS = "How many people? ";
    public static final String ENTER_PLAYER = "플레이어 %d의 이름은?(3 english letters): ";
    public static final String ENTER_PINS = "'s turn : ";

    private static final Scanner scanner = new Scanner(System.in);
    public static final int MAX_PINS = 10;
    public static final int INDEX_ONE = 1;

    public static Players getPlayers() {
        print(ENTER_PLAYERS);

        int playersCount = scanner.nextInt();
        Players players = new Players();
        for (int playerIndex = INDEX_ONE; playerIndex < playersCount + INDEX_ONE; playerIndex++) {
            printf(ENTER_PLAYER, playerIndex);
            players.add(new Player(scanner.next()));
        }
        return players;
    }

    public static Pins getPins(Player player) {
        print(player.getName() + ENTER_PINS);
        return new Pins(scanner.nextInt());
    }

    public static Pins getSecondPins(State state, Player player) {
        print(player.getName() + ENTER_PINS);
        int pins = scanner.nextInt();
        int remainPins = MAX_PINS - state.getScore().getFirst().get();
        if (pins > remainPins) {
            throw new IllegalArgumentException(String.format("남은 핀은 %d개입니다.", remainPins));
        }
        return new Pins(pins);
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
