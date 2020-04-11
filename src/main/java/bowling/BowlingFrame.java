package bowling;

import java.util.List;

public class BowlingFrame {
    private static final int COMMON_FRAME_FULL_PITCH_COUNT = 2;
    private static final int LAST_FRAME_FULL_PITCH_COUNT = 3;

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
            return frameScore.isOver(LAST_FRAME_FULL_PITCH_COUNT, !frameScore.canThrowThirdPitching());
        }

        return frameScore.isOver(COMMON_FRAME_FULL_PITCH_COUNT, frameScore.isStrikeAtFirstScore());
    }
}
