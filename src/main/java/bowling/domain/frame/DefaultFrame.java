package bowling.domain.frame;

public class DefaultFrame extends Frame {

    private static final int TOTAL_CHANCE = 2;
    private static final int SPARE_BONUS_SIZE = 1;
    private static final int STRIKE_BONUS_SIZE = 2;

    @Override
    protected int TotalChance() {
        return TOTAL_CHANCE;
    }

    public void minusChance() {
        if (this.scores.first().isStrike()) {
            this.chance.minusTwo();
            return;
        }
        this.chance.minusOne();
    }

    @Override
    protected void validateScore(Frame frame) {
        if (this.scores.sum() > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isNotEndScoreAggregation() {
        if (this.chance.isRemainChance()) {
            return true;
        }
        if (this.scores.first().isStrike()) {
            return !this.bonusScores.isSizeEqual(STRIKE_BONUS_SIZE);
        }
        if (isSpare()) {
            return !this.bonusScores.isSizeEqual(SPARE_BONUS_SIZE);
        }
        return false;
    }
}
