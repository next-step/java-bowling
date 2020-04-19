package seul.bowling.domain.status;

import lombok.Getter;
import seul.bowling.domain.Pins;
import seul.bowling.domain.Score;

public class Status {
    private static final int BONUS_PLAY = 0;
    private static final int BONUS_SCORE_COUNT = 0;
    private static final boolean STRIKE = false;

    @Getter
    Pins pins;
    @Getter
    Score score;

    protected Status(Score score, Pins pins) {
        this.score = score;
        this.pins = pins;
    }

    public Status() {
        this.score = new Score();
        this.pins = new Pins();
    }

    public boolean endJudgmentStatus() {
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
            return new Strike(this.score, this.pins);
        }

        return new Hold(this.score, this.pins);
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

    public void addPins(int clearPin, boolean isBonusPlay) {
        pins.addPin(clearPin, isBonusPlay);
    }

    public boolean pinsAllClear() {
        return pins.allClear();
    }

    public boolean pinsEndDefaultPlayCount() {
        return pins.endDefaultPlayCount(STRIKE);
    }
}
