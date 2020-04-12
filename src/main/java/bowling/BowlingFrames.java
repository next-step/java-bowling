package bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingFrames {
    private static final int MAX_BOWLING_FRAME_SIZE = 10;

    private final List<BowlingFrame> frames;

    public BowlingFrames() {
        frames = new ArrayList<>();
        frames.add(CommonBowlingFrame.newInstance());
    }

    private BowlingFrames(final List<BowlingFrame> frames) {
        this.frames = Collections.unmodifiableList(frames);
    }

    public int overFrameSize() {
        if (lastBowledFrame().isOver()) {
            return frames.size();
        }

        return frames.size() - 1;
    }

    public void bowl(final int pinCount) {
        BowlingFrame bowlingFrame = lastBowledFrame();
        bowlingFrame.bowl(pinCount);

        if (bowlingFrame.isOver()) {
            addNextFrame();
        }
    }

    private void addNextFrame() {
        if (frames.size() == MAX_BOWLING_FRAME_SIZE - 1) {
            frames.add(LastBowlingFrame.newInstance());
            return;
        }
        frames.add(CommonBowlingFrame.newInstance());
    }

    private BowlingFrame lastBowledFrame() {
        return frames.get(frames.size() - 1);
    }
}
