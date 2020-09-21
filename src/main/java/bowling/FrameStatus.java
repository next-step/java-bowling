package bowling;

import java.util.function.Function;

public enum FrameStatus {

    STRIKE(frame -> {
        Frame nextFrame = frame.getNextFrame();
        if (nextFrame.isLastFrame() && nextFrame.isStrike()) {
            return nextFrame.isDone();
        }
        if (nextFrame.isStrike()) {
            Frame nextNextFrame = nextFrame.getNextFrame();
            return nextNextFrame.isFirstDone();
        }
        return nextFrame.isDone();
    }),
    SPARE(frame -> {
        Frame nextFrame = frame.getNextFrame();
        return nextFrame.isFirstDone();
    }),
    MISS(Frame::isDone);

    private final Function<Frame, Boolean> canCalculateScore;

    FrameStatus(Function<Frame, Boolean> canCalculateScore) {
        this.canCalculateScore = canCalculateScore;
    }

    public static FrameStatus of(Frame frame) {
        if (frame.isStrike()) {
            return STRIKE;
        }
        if (frame.isSpare()) {
            return SPARE;
        }
        return MISS;
    }

    public boolean canCalculateScore(Frame frame) {
        return canCalculateScore.apply(frame);
    }
}
