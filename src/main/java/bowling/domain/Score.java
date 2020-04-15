package bowling.domain;

import lombok.Getter;

public class Score {
    private static final int ZERO = 0;
    private static final int STRIKE_BONUS_COUNT = 2;
    private static final int SPARE_BONUS_COUNT = 1;

    @Getter
    private int bonusAddCount;
    @Getter
    private int totalScore;

    public void update(RoundsStatus status, int clearPinCount, boolean isLastFrame) {
        if (status.isStrike() && bonusAddCount == ZERO && !isLastFrame) {
            this.bonusAddCount += STRIKE_BONUS_COUNT;
        }

        if (status.isSpare() && bonusAddCount == ZERO && !isLastFrame) {
            this.bonusAddCount += SPARE_BONUS_COUNT;
        }

        addTotalScore(clearPinCount);
    }

    public void updateBonus(int bonusScore) {
        bonusAddCount--;
        addTotalScore(bonusScore);
    }

    public boolean availableBonus() {
        return bonusAddCount > ZERO;
    }

    public void addScore(int score) {
        this.totalScore += score;
    }

    private void addTotalScore(int addScore) {
        this.totalScore += addScore;
    }
}
