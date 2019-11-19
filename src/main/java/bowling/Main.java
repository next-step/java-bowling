package bowling;

import bowling.domain.Frame;
import bowling.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int FRAME_COUNT = 9;

    public static void main(String[] args) {

        InputView.createUser();

        GameFrames gameFrames = new GameFrames(createFrames());

    }

    private static List<Frame> createFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i <= FRAME_COUNT; i++) {
            frames.add(createFrame(i));
        }
        return frames;
    }

    private static Frame createFrame(int frameIndex) {
        Frame frame = new Frame();
        while (!frame.isEnd()) {
            int score = InputView.createScore(frameIndex);
            frame.addScore(score);
        }

        return frame;
    }
}
