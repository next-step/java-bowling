package bowling.domain.state.end;

import bowling.domain.Pins;
import bowling.domain.frame.Score;

public class Strike extends EndedState {

    private static final String STRIKE_SYMBOL = "X";

    @Override
    public String symbol() {
        return STRIKE_SYMBOL;
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        if (beforeScore.isCalculatorScore()) {
            return beforeScore;
        }
        return beforeScore.bowl(Pins.MAX_RANGE);
    }
}
