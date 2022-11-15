package bowling.domain.frame;

import bowling.domain.Chance;
import bowling.domain.score.TotalScore;

public class LastFrame extends Frame {

    private static final int TOTAL_CHANCE = 3;

    public LastFrame() {
        super(new Chance(TOTAL_CHANCE), TotalScore.lastFrameTotalScore());
    }

    @Override
    public boolean isNotEndScoreAggregation() {
        return this.chance.isRemainChance();
    }
}
