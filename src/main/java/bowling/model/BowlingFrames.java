package bowling.model;

import java.util.LinkedList;
import java.util.List;

public class BowlingFrames {

    public static final int MAX_BOWLING_FRAME_SIZE = 10;

    private final LinkedList<BowlingFrame> frames;

    public BowlingFrames() {
        this.frames = new LinkedList<>();
        frames.add(CommonBowlingFrame.newInstance());
    }

    public boolean isAllFrameOver() {
        if (getLastBowledFrame().isOver()) {
            return frames.size() == MAX_BOWLING_FRAME_SIZE;
        }

        return false;
    }

    public void bowl(final int pinCount) {
        validateBowlable();

        BowlingFrame bowlingFrame = getLastBowledFrame();
        bowlingFrame.bowl(pinCount);

        if (bowlingFrame.isOver() && frames.size() < MAX_BOWLING_FRAME_SIZE) {
            frames.add(getNextFrame());
        }
    }

    private void validateBowlable() {
        if (isAllFrameOver()) {
            throw new IllegalStateException("모든 프레임이 종료되었습니다.");
        }
    }

    private BowlingFrame getNextFrame() {
        if (frames.size() == MAX_BOWLING_FRAME_SIZE - 1) {
            return LastBowlingFrame.newInstance();
        }
        return CommonBowlingFrame.newInstance();
    }

    private BowlingFrame getLastBowledFrame() {
        return frames.getLast();
    }

    public int size() {
        return frames.size();
    }

    public List<BowlingFrame> getFrames() {
        return frames;
    }
}
