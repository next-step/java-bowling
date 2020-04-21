package bowling.domain.frame;

import bowling.domain.score.ScoreStatus;

public class FinalFrame extends Frame {
    private static final int SCOREABLE_TRY_COUNT = 2;

    public FinalFrame() {
        super();
    }

    @Override
    public boolean isThrowable() {
        return points.isThrowableForFinalFrame();
    }

    @Override
    public void addScore() {
        if (this.score == null && (points.isScore(ScoreStatus.STRIKE) || points.isTryCount(SCOREABLE_TRY_COUNT))) {
            this.score = points.makeScore();
        }
    }
}
