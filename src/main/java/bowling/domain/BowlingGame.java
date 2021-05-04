package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private static int MAX_FRAMES_GAME_HOLD = 11;
    private static int FINAL_FRAME_NUMBER = 10;
    private static int BEFORE_FINAL_FRAME = 8;

    private List<Frame> frames;

    public BowlingGame() {
        frames = new ArrayList<>();
        frames.add(new NormalFrame());
    }

    public boolean isAvailable() {
        return getCurrentFrame().isAvailable() && (frames.size() <= MAX_FRAMES_GAME_HOLD);
    }

    public int bowl(int inputScore) {
        return getCurrentFrame().bowl(inputScore).getStateValue();
    }

    public int nextFrameIfAvailable() {
        if (!getCurrentFrame().isAvailable()) {
            int frameNumber = frames.size() - 1;
            frames.add(getCurrentFrame().createFrame(isNextFinalFrame(frameNumber)));
        }
        return getFrameNumber();
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    private boolean isNextFinalFrame(int frameNumber) {
        if (frameNumber >= BEFORE_FINAL_FRAME) {
            return true;
        }
        return false;
    }

    private int getFrameNumber() {
        if (frames.size() > FINAL_FRAME_NUMBER) {
            return FINAL_FRAME_NUMBER;
        }
        return frames.size();
    }
}
