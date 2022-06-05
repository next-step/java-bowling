package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Strike extends EndState {
    private static final String STRIKE_SYMBOL = "X";

    @Override
    public String symbol() {
        return STRIKE_SYMBOL;
    }

    @Override
    public Score score() {
        return Score.strike();
    }

    @Override
    public Score calculateAdditionalScore(Score previousScore) {
        if (previousScore.isNoBonusChance()) {
            return previousScore;
        }
        return previousScore.addScore(Pins.MAX_HIT_COUNT);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}