package seul.bowling.domain.status;

import seul.bowling.domain.Pins;
import seul.bowling.domain.Score;

public class Hold extends Status {
    private static final int BONUS_SCORE_COUNT = 0;

    public Hold(Score score, Pins pins) {
        super(score, pins);
    }

    @Override
    public Status judgmentStatus(boolean allClear) {
        if (allClear) {
            return new Spare(this.score, this.pins);
        }

        return new Miss(this.score, this.pins);
    }

    @Override
    public boolean endJudgmentStatus() {
        return false;
    }

    @Override
    public void addScore(int clearPin) {
        this.score.addScore(clearPin, BONUS_SCORE_COUNT);
    }
}
