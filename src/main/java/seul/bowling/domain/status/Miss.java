package seul.bowling.domain.status;

import seul.bowling.domain.Score;

public class Miss extends Status {
    private static final int BONUS_SCORE_COUNT = 0;

    public Miss(Score score) {
        super(score);
    }

    @Override
    public Status judgmentStatus(boolean allClear) {
        return this;
    }

    @Override
    public boolean endJudgmentStatus() {
        return true;
    }

    @Override
    public void addScore(int clearPin) {
        this.score.addScore(clearPin, BONUS_SCORE_COUNT);
    }

    @Override
    public boolean endScore() {
        return true;
    }
}
