package bowling.domain.frame;

import bowling.domain.bowlinggame.BowlingGameResults;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int NUM_TOTAL_FRAME = 10;
    private static final int NUM_NORMAL_FRAME = NUM_TOTAL_FRAME - 1;

    private final List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
        frames.add(new NormalFrame());
    }

    public void record(int numDownedPin) {
        renewFrame();
        recordAdditionalScore(numDownedPin);
        getLatestFrame().record(numDownedPin);
    }

    private void recordAdditionalScore(int numDownedPin) {
        for (int i = 0; i < NUM_NORMAL_FRAME && i < frames.size(); i++) {
            ((NormalFrame) frames.get(i)).recordAdditionalScore(numDownedPin);
        }
    }

    private void renewFrame() {
        if (getLatestFrame().isEnd() && frames.size() == NUM_NORMAL_FRAME) {
            frames.add(new LastFrame());
            return;
        }

        if (getLatestFrame().isEnd()) {
            frames.add(new NormalFrame());
        }
    }

    public boolean isEnd() {
        return frames.size() == NUM_TOTAL_FRAME && getLatestFrame().isEnd();
    }

    public BowlingGameResults exportResult() {
        return new BowlingGameResults(getCurrentFrameNumber(), frames);
    }

    private int getCurrentFrameNumber() {
        if (getLatestFrame().isEnd()) {
            return frames.size() + 1;
        }

        return frames.size();
    }

    private Frame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }
}
