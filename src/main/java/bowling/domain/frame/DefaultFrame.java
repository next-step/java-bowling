package bowling.domain.frame;

import bowling.domain.Chance;
import bowling.domain.score.TotalScore;

public class DefaultFrame extends Frame {

    private static final int TOTAL_CHANCE = 2;

    public DefaultFrame() {
        super(new Chance(TOTAL_CHANCE), TotalScore.defaultFrameTotalScore());
    }

    @Override
    public boolean isNotEndScoreAggregation() {
        return this.chance.isRemainChance() || this.totalScore.isNotEndScoreAggregation();
    }
}
