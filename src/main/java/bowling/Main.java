package bowling;

import bowling.domain.Frame;
import bowling.domain.GameResult;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int FRAME_COUNT = 9;

    public static void main(String[] args) {

        String user = InputView.createUser();

        GameResult gameFrames = new GameResult(createFrames(user));

    }

    private static List<Frame> createFrames(String user) {
        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i <= FRAME_COUNT; i++) {
            frames.add(createFrame(frames, i, user));
        }
        return frames;
    }

    private static Frame createFrame(List<Frame> frames, int frameIndex, String user) {
        Frame frame = new Frame();
        while (!frame.isEnd()) {
            int score = InputView.createScore(frameIndex);
            frame.addScore(score);
            ResultView.printScore(user, frame, frames);
        }

        return frame;
    }
}
