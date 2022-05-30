package bowling.domain.state;

import bowling.domain.score.Score;

public class Strike extends EndedState {
    private static final String SYMBOL = "X";

    public Strike(Pin pin) {
        super(pin);
    }

    @Override
    public Score score() {
        return Score.strike(pin);
    }

    @Override
    public String toString() {
        return SYMBOL;
    }

    @Override
    public String toSimpleString() {
        return toString();
    }
}
