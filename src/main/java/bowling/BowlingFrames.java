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

    public boolean isAllFrameOver() {
        if (lastBowledFrame().isOver()) {
            return frames.size() == MAX_BOWLING_FRAME_SIZE;
        }

        return false;
    }

    public void bowl(final int pinCount) {
        BowlingFrame bowlingFrame = lastBowledFrame();
        bowlingFrame.bowl(pinCount);

        if (bowlingFrame.isOver() && frames.size() < MAX_BOWLING_FRAME_SIZE) {
            frames.add(nextFrame());
        }
    }

    private BowlingFrame nextFrame() {
        if (frames.size() == MAX_BOWLING_FRAME_SIZE - 1) {
            return LastBowlingFrame.newInstance();
        }
        return CommonBowlingFrame.newInstance();
    }

    private BowlingFrame lastBowledFrame() {
        return frames.get(frames.size() - 1);
    }
}
