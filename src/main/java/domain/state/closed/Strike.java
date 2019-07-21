package domain.state.closed;

import domain.Score;

import static domain.Pins.STRIKE_PINS;

public class Strike extends Closed {
    private static final String STRIKE_SYMBOL = "X";

    @Override
    public String printState() {
        return STRIKE_SYMBOL;
    }

    @Override
    public Score getScore() {
        return Score.of(STRIKE_PINS, 2);
    }
}
