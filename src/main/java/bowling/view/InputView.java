package bowling.view;

import java.io.PrintStream;
import java.util.Scanner;

public class InputView {

    public static final String GET_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    public static final String GET_FRAME_SCORE = "%d프레임 투구 : ";
    private static final Scanner scanner = new Scanner(System.in);
    private static final PrintStream out = System.out;

    public static String getName() {
        out.print(GET_PLAYER_NAME);
        return scanner.next();
    }

    public static int getScore(int frameIdx) {
        out.printf(GET_FRAME_SCORE, frameIdx);
        return scanner.nextInt();
    }
}
