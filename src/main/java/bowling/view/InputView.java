package bowling.view;

import bowling.domain.Player;

import java.io.PrintStream;
import java.util.Scanner;

public class InputView {

    public static final String GET_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    public static final String GET_FRAME_SCORE = "%s's turn : ";
    public static final String PLAYER_NUM = "How many people? ";
    private static final Scanner scanner = new Scanner(System.in);
    private static final PrintStream out = System.out;

    public static String getName() {
        out.print(GET_PLAYER_NAME);
        return scanner.next();
    }

    public static int getScore(Player player) {
        out.printf(GET_FRAME_SCORE, player.getName());
        return scanner.nextInt();
    }

    public static int getPlayerNum() {
        out.print(PLAYER_NUM);
        return scanner.nextInt();
    }
}
