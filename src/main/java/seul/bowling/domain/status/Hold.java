package seul.bowling.domain.status;

import seul.bowling.domain.Score;

public class Hold extends Status {
    private static final int BONUS_SCORE_COUNT = 0;

    public Hold(Score score) {
        super(score);
    }

    @Override
    public Status judgmentStatus(boolean allClear) {
        if (allClear) {
            return new Spare(this.score);
        }

        return new Miss(this.score);
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
