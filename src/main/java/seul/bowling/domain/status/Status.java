package seul.bowling.domain.status;

import lombok.Getter;
import seul.bowling.domain.Score;

public class Status {
    private static final int BONUS_PLAY = 0;
    private static final int BONUS_SCORE_COUNT = 0;

    @Getter
    Score score;

    protected Status(Score score) {
        this.score = score;
    }

    public Status() {
        this.score = new Score();
    }

    public boolean endJudgmentStatus() {
        return false;
    }

    public boolean isStrike() {
        return false;
    }

    public boolean isSpare() {
        return false;
    }

    public int getBonusPlay() {
        return BONUS_PLAY;
    }

    public void addScore(int clearPin) {
        this.score.addScore(clearPin, BONUS_SCORE_COUNT);
    }

    public Status judgmentStatus(boolean allClear) {
        if (allClear) {
            return new Strike(this.score);
        }

        return new Hold(this.score);
    }

    public void addCumulativeScore(int score) {
        this.score.addCumulativeScore(score);
    }

    public void addBonusScore(int bonusScore) {
        score.addBonusScore(bonusScore);
    }

    public boolean endScore() {
        return false;
    }

    public boolean availableAddBonusScore() {
        return !score.bonusScoreCountIsEmpty();
    }

    public int getToTalScore() {
        return this.score.getScore();
    }
}
