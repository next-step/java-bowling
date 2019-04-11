package domain.status;

import domain.pin.Pin;
import domain.score.Score;

import static domain.pin.Pin.MAXIMUM_PINS;

public class Strike extends FrameFinished {
    private static final int STRIKE_BONUS_COUNT = 2;
    static final String STRIKE_DISPLAY_STRING = "X";

    public Strike(Pin first) {
        super(first);

        if (first.getPin() != MAXIMUM_PINS) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isClear() {
        return true;
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }

    @Override
    public String toString() {
        return STRIKE_DISPLAY_STRING;
    }
}