package bowling.view;

import bowling.domain.BowlingGame;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_FRAME_PITCHING = "프레임 투구 : ";

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME);
        return SCANNER.nextLine();
    }

    public static int inputFramePitching(final BowlingGame frames) {
        System.out.print(frames.currentFrameNumber() + INPUT_FRAME_PITCHING);
        return Integer.parseInt(SCANNER.nextLine());
    }
}
