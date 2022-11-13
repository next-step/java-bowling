package bowling.domain.frame;

import bowling.domain.Chance;
import bowling.domain.score.TotalScore;

public class DefaultFrame extends Frame {

    private static final int TOTAL_CHANCE = 2;
    private static final int SPARE_BONUS_SIZE = 1;
    private static final int STRIKE_BONUS_SIZE = 2;

    public DefaultFrame() {
        super(new Chance(TOTAL_CHANCE), TotalScore.defaultFrameTotalScore());
    }

    @Override
    public void minusChance() {
        if (this.totalScore.regularScores().first().isStrike()) {
            this.chance.minusTwo();
            return;
        }
        this.chance.minusOne();
    }

    @Override
    public boolean isNotEndScoreAggregation() {
        if (this.chance.isRemainChance()) {
            return true;
        }
        if (this.totalScore.regularScores().first().isStrike()) {
            return !this.totalScore.bonusScores().isSizeEqual(STRIKE_BONUS_SIZE);
        }
        if (this.totalScore.regularScores().isSpare()) {
            return !this.totalScore.bonusScores().isSizeEqual(SPARE_BONUS_SIZE);
        }
        return false;
    }
}
