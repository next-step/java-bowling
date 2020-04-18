package seul.bowling.view;

import seul.bowling.domain.Frame;
import seul.bowling.domain.Frames;

import java.util.Scanner;

public class InputView {
    private static final String PLAYER_NAME = "플레이어 이름은(3 english letters)?:\n";
    private static final String CLEAR_PIN = "%d프레임 투구 :\n";

    private Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String inputPlayerName() {
        System.out.printf(PLAYER_NAME);

        return scanner.nextLine();
    }

    public int inputClearPin(Frames frames) {
        int lastFrameIndex = frames.currentFrameIndex();

        boolean endFrame = false;
        if (!frames.isEmpty()) {
            Frame frame = frames.getFrame(lastFrameIndex);
            endFrame = frame.endFrame();
        }

        if (endFrame) {
            lastFrameIndex++;
        }

        System.out.printf(CLEAR_PIN, ++lastFrameIndex);

        return scanner.nextInt();
    }
}
