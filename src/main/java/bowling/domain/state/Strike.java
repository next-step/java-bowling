package bowling.domain.state;

import bowling.domain.Hit;
import bowling.domain.Score;

public class Strike extends Finished {

    private static final String SYMBOL = "X";
    private static final int STRIKE_HIT = 10;

    @Override
    public boolean hasBonusChance() {
        return true;
    }

    @Override
    public int bowlingCount() {
        return ONE_HIT;
    }

    @Override
    public String description() {
        return SYMBOL;
    }

    @Override
    public Score score() {
        return Score.strike();
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return score.addAdditionalScore(Hit.valueOf(STRIKE_HIT));
    }
}
