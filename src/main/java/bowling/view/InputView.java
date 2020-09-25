package bowling.view;

import bowling.domain.player.Player;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_MESSAGE = "플레이어 이름은(3 english letters)? : ";
    private static final String INPUT_SCORE_MESSAGE = "%d 프레임 투구 : ";
    private static final int INCREASE_FRAME_NUMBER = 1;
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static Player inputPlayer() {
        System.out.println(INPUT_PLAYER_MESSAGE);
        return Player.of(SCANNER.nextLine());
    }

    public static int inputScore(int frameIndex) {
        System.out.println(String.format(INPUT_SCORE_MESSAGE, frameIndex + INCREASE_FRAME_NUMBER));
        return SCANNER.nextInt();
    }
}
