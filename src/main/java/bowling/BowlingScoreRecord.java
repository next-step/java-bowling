package bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingScoreRecord {
    private static final int BOWLING_ROUND_COUNT = 10;
    private final List<Frame> frames;

    public BowlingScoreRecord() {
        this.frames = new ArrayList<>();
        for (int i = 0; i < BOWLING_ROUND_COUNT; i++) {
            this.frames.add(new Frame());
        }
    }

    public int hit(int frameNumber, int count) {
        Frame frame = frames.get(frameNumber);

        if (frame.hitBowlingPin(count)) {
            return frameNumber + 1;
        }
        return frameNumber;
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
