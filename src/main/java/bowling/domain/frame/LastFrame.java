package bowling.domain.frame;

import bowling.domain.Chance;
import bowling.domain.score.TotalScore;

public class LastFrame extends Frame {

    private static final int TOTAL_CHANCE = 3;
    private static final int DEFAULT_CHANCE = 2;

    public LastFrame() {
        super(new Chance(TOTAL_CHANCE), TotalScore.lastFrameTotalScore());
    }

    @Override
    public void minusChance() {
        if (this.totalScore.regularScores().isSizeEqual(DEFAULT_CHANCE)
                && !this.totalScore.regularScores().first().isStrike()
                && !this.totalScore.regularScores().isSpare()) {
            this.chance.minusTwo();
            return;
        }
        this.chance.minusOne();
    }

    @Override
    public boolean isNotEndScoreAggregation() {
        return this.chance.isRemainChance();
    }
}
