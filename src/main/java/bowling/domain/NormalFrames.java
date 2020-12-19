package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrames {

    private static final int MAXIMUM_NUM_NORMAL_FRAME = 9;

    private final List<NormalFrame> frames;

    public NormalFrames() {
        frames = new ArrayList<>();
    }

    public void record(DownedPin currentTry) {
        if (frames.size() == 0 || getLatestFrame().isEnd()) {
            frames.add(new NormalFrame());
        }

        getLatestFrame().record(currentTry);
    }

    public boolean isEnd() {
        return frames.size() >= MAXIMUM_NUM_NORMAL_FRAME && getLatestFrame().isEnd();
    }

    private NormalFrame getLatestFrame() {
        return frames.get(frames.size() - 1);
    }
}
