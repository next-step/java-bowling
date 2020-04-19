package seul.bowling.domain.status;

import seul.bowling.domain.Score;

public class Spare extends Status {
    private static final int BONUS_PLAY = 1;
    private static final int BONUS_SCORE_COUNT = 1;

    public Spare(Score score) {
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

    @Override
    public boolean isSpare() {
        return true;
    }
}
