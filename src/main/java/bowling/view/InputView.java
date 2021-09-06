package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String GET_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String GET_FRAME_SCORE = "프레임 투구 : ";

    public static String getPlayerName() {
        System.out.print(GET_PLAYER_NAME);
        String player = scanner.nextLine();
        return player;
    }

    public static int getFrameScore(int frameNumber) {
        System.out.print(frameNumber + GET_FRAME_SCORE);
        int score = scanner.nextInt();
        return score;
    }
}
