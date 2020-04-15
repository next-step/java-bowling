package bowling.domain;

import lombok.Getter;

public class ScoreStatus {
    @Getter
    private RoundsStatus status;
    @Getter
    private Score score;

    public ScoreStatus() {
        this.status = RoundsStatus.NONE;
        this.score = new Score();
    }

    public void update(int roundIndex, int clearPinCount, int totalClearPin, boolean isLastFrame) {
        if (isNone()) {
            this.status = RoundsStatus.getRoundStatus(roundIndex, totalClearPin);
        }

        this.score.update(this.status, clearPinCount, isLastFrame);
    }

    public void updateBonus(int bonusScore) {
        score.updateBonus(bonusScore);
    }

    public boolean availableBonus() {
        return score.availableBonus();
    }

    public boolean endCalculate() {
        return status != RoundsStatus.NONE && !score.availableBonus();
    }

    public void addScore(int score) {
        this.score.addScore(score);
    }

    public int getTotalScore() {
        return score.getTotalScore();
    }

    public boolean isStrike() {
        return this.status.isStrike();
    }

    public boolean isSpare() {
        return this.status.isSpare();
    }

    public boolean isNone() {
        return this.status.isNone();
    }
}
