package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.Frames;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_FRAME_PITCHING = "프레임 투구 : ";

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME);
        return scanner.nextLine();
    }

    public static int inputFramePitching(final Frames frames) {
        System.out.print(frames.valueOfCurrentFrameNumber() + INPUT_FRAME_PITCHING);
        return Integer.parseInt(scanner.nextLine());
    }
}
