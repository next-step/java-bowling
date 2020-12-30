package bowling.domain.bowlinggame;

import bowling.domain.frame.Frame;
import bowling.domain.frame.LastFrame;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameBoard {

    private static final int NUM_TOTAL_FRAME = 10;
    private static final int NUM_NORMAL_FRAME = NUM_TOTAL_FRAME - 1;

    private final List<Frame> frames;

    public BowlingGameBoard() {
        this.frames = new ArrayList<>();
        frames.add(new NormalFrame());
    }

    public void record(int numDownedPin) {
        renewFrame();
        getLatestFrame().record(numDownedPin);
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

    public BowlingStatus exportStatus() {
        return new BowlingStatus(getCurrentFrameNumber(), frames);
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
