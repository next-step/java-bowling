package bowling.view;

import bowling.domain.player.Player;

import java.util.Scanner;

public class InputView {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static String NAME_FORMAT = "플레이어 %d의 이름은?(3 english letters)?: ";
    private final static String PLAYER_TURN_FORMAT = "%s's turn : ";

    public static String inputPlayerName(int index) {
        System.out.println(String.format(NAME_FORMAT, index + 1));
        return SCANNER.next();
    }

    public static int inputScore(Player player) {
        System.out.println(String.format(PLAYER_TURN_FORMAT, player.getName()));
        return SCANNER.nextInt();
    }

    public static int inputPlayerCount() {
        System.out.println("How many people? ");
        return SCANNER.nextInt();
    }
}
