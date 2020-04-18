package bowling.refactor;

import java.util.Objects;

public class FrameScore {
    private Score score;
    private LeftScoreCount leftScoreCount;

    private FrameScore(final Score score, final LeftScoreCount leftScoreCount) {
        validateNull(score, leftScoreCount);
        this.score = score;
        this.leftScoreCount = leftScoreCount;
    }

    private void validateNull(final Score score, final LeftScoreCount leftScoreCount) {
        if (Objects.isNull(score) || Objects.isNull(leftScoreCount)) {
            throw new IllegalArgumentException("score and LeftScoreCount must be not null.");
        }
    }

    public static FrameScore newInstance(final Score score, final LeftScoreCount leftScoreCount) {
        return new FrameScore(score, leftScoreCount);
    }

    public Score getScore() {
        if (!canCalculateScore()) {
            throw new IllegalStateException();
        }
        return score;
    }

    private boolean canCalculateScore() {
        return leftScoreCount.isEqualTo(0);
    }
}
