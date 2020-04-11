package bowling;

import java.util.List;

public class BowlingFrame {

    private final FrameScore frameScore;
    private final boolean isLastFrame;

    private BowlingFrame(final FrameScore frameScore, final boolean isLastFrame) {
        this.frameScore = frameScore;
        this.isLastFrame = isLastFrame;
    }

    public static BowlingFrame newInstance(final boolean isLastFrame) {
        return new BowlingFrame(new FrameScore(), isLastFrame);
    }

    public static BowlingFrame newInstance(final List<Integer> scoreNumbers, final boolean isLastFrame) {
        return new BowlingFrame(FrameScore.newInstance(scoreNumbers), isLastFrame);
    }

    public void pitch(final int scoreCount) {
        if (isOver()) {
            throw new RuntimeException("can not pitch the bowl at this frame.");
        }
        frameScore.add(scoreCount);
    }

    public boolean isOver() {
        if (isLastFrame) {
            return frameScore.isOverLastFrame();
        }

        return frameScore.isOverCommonFrame();
    }
}
