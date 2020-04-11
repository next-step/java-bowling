package bowling;

import java.util.List;

public class BowlingFrame {
    private static final int COMMON_FRAME_FULL_PITCH_COUNT = 2;
    private static final int LAST_FRAME_FULL_PITCH_COUNT = 3;

    private final FrameScore2 frameScore;
    private final boolean isLastFrame;

    private BowlingFrame(final FrameScore2 frameScore, final boolean isLastFrame) {
        this.frameScore = frameScore;
        this.isLastFrame = isLastFrame;
    }

    public static BowlingFrame newInstance(final boolean isLastFrame) {
        return new BowlingFrame(new FrameScore2(), isLastFrame);
    }

    public static BowlingFrame newInstance(final List<Integer> frameScore, final boolean isLastFrame) {
        return new BowlingFrame(FrameScore2.newInstance(frameScore), isLastFrame);
    }

    public boolean isOver() {
        if (isLastFrame) {
            return frameScore.isOver(LAST_FRAME_FULL_PITCH_COUNT, !frameScore.hasStrikeOrSpare());
        }

        return frameScore.isOver(COMMON_FRAME_FULL_PITCH_COUNT, frameScore.isStrike());
    }
}
