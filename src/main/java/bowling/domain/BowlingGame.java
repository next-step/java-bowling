package bowling.domain;

import bowling.domain.score.FrameScore;
import java.util.HashMap;
import java.util.Map;

public class BowlingGame {
    private Frame frame;
    private int currentFrameNumber;

    public BowlingGame() {
        frame = new NormalFrame();
        currentFrameNumber = 1;
    }

    public boolean isAvailable() {
        return frame.isAvailable();
    }

    public int bowling(String inputScore) {
        return frame.addScore(Integer.parseInt(inputScore));
    }

    public int nextFrameIfAvailable() {
        if (!frame.isAvailable() && !(frame instanceof FinalFrame)) {
            startNewFrame();
        }
        return currentFrameNumber;
    }

    private void startNewFrame() {
        frame = frame.createFrame(++currentFrameNumber);
    }
}
