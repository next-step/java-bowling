package bowling.domain.frame;

import bowling.domain.score.ScoreStatus;
import bowling.domain.status.running.FinalReady;

public class FinalFrame extends Frame {
    private static final int SCOREABLE_TRY_COUNT = 2;

    public FinalFrame() {
        super();
        this.status = FinalReady.of();
    }

    @Override
    public void addScore() {
        if (this.score == null && (points.isScore(ScoreStatus.STRIKE) || points.isTryCount(SCOREABLE_TRY_COUNT))) {
            this.score = points.makeScore();
        }
    }
}
