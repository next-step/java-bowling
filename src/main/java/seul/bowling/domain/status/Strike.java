package seul.bowling.domain.status;

import seul.bowling.domain.Score;

public class Strike extends Status {
    private static final int BONUS_PLAY = 2;
    private static final int BONUS_SCORE_COUNT = 2;

    public Strike(Score score) {
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
    public boolean isStrike() {
        return true;
    }

    @Override
    public int getBonusPlay() {
        return BONUS_PLAY;
    }

    @Override
    public void addScore(int clearPin) {
        this.score.addScore(clearPin, BONUS_SCORE_COUNT);
    }

    @Override
    public boolean endScore() {
        return !availableAddBonusScore();
    }
}
