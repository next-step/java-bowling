package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NewBowlingGame {

    private final static int TOTAL_FRAME_SIZE = 10;
    private final static int NUM_NORMAL_FRAMES = TOTAL_FRAME_SIZE - 1;

    List<Frame> frames;

    public NewBowlingGame() {
        this.frames = new ArrayList<>();
    }

    public void record(DownedPin downedPin) {
        if (frames.size() == 0) {
            frames.add(new NormalFrame());
        }

        renewFrame();

        getLatestFrame().record(downedPin);
    }

    private void renewFrame() {
        if (!getLatestFrame().isEnd()) {
            return;
        }

        if (frames.size() == NUM_NORMAL_FRAMES && getLatestFrame().isEnd()) {
            frames.add(new NewLastFrame());
            return;
        }

        frames.add(new NormalFrame());
    }

    private Frame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }

    public boolean isEnd() {
        return frames.size() == TOTAL_FRAME_SIZE && getLatestFrame().isEnd();
    }
}
