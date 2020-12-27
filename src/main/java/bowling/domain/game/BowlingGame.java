package bowling.domain.game;

import bowling.domain.frame.DownedPin;
import bowling.domain.frame.Frame;
import bowling.domain.frame.LastFrame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.viewexporter.BowlingGameInformation;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private final static int TOTAL_FRAME_SIZE = 10;
    private final static int NUM_NORMAL_FRAMES = TOTAL_FRAME_SIZE - 1;

    private final List<Frame> frames;


    public BowlingGame() {
        this.frames = new ArrayList<>();
    }

    public void record(DownedPin downedPin) {
        if (frames.size() == 0) {
            frames.add(new NormalFrame());
        }
        renewFrame();
        getLatestFrame().record(downedPin);
    }

    public boolean isEnd() {
        return frames.size() == TOTAL_FRAME_SIZE && getLatestFrame().isEnd();
    }

    private void renewFrame() {
        if (!getLatestFrame().isEnd()) {
            return;
        }

        if (frames.size() == NUM_NORMAL_FRAMES && getLatestFrame().isEnd()) {
            frames.add(new LastFrame());
            return;
        }

        frames.add(new NormalFrame());
    }

    private Frame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }

    public BowlingGameInformation createInformation() {
        return new BowlingGameInformation(frames, getCurrentFrame());
    }

    private int getCurrentFrame() {
        if (frames.isEmpty()) {
            return 1;
        }

        if (getLatestFrame().isEnd()) {
            return frames.size() + 1;
        }

        return frames.size();
    }
}
