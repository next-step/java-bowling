package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Strike extends EndedState {
    private static final String STRIKE_SYMBOL = "X";

    public static ThrowingState create() {
        return new Strike();
    }

    @Override
    public String symbol() {
        return STRIKE_SYMBOL;
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public Score score() {
        return Score.of(Pins.MAX_PINS, 2);
    }

    @Override
    public Score scoreAfter(Score prevScore) {
        return prevScore.bowl(Score.withNonRemainingPitches(Pins.MAX_PINS));
    }
}
