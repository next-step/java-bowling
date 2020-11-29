package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.last.LastGutter;
import bowling.domain.state.last.LastOrdinary;
import bowling.domain.state.last.LastStrike;

import static bowling.domain.pin.Pin.MAX_PINS;
import static bowling.domain.pin.Pin.MIN_PINS;

public class Strike implements State {
    private final int leftTry;

    public Strike(int leftTry) {
        this.leftTry = leftTry;
    }

    @Override
    public State record(int pins) {
        if (leftTry == MIN_LEFT_TRY) {
            return recordLast(pins);
        }
        if (pins == MAX_PINS) {
            return new Strike(leftTry - 1);
        }
        if (pins == MIN_PINS) {
            return new Gutter(leftTry - 1);
        }
        return new Ordinary(pins, leftTry - 1);
    }

    private State recordLast(int pins) {
        if (pins == MAX_PINS) {
            return new LastStrike();
        }
        if (pins == MIN_PINS) {
            return new LastGutter();
        }
        return new LastOrdinary(pins);
    }

    @Override
    public Score getScore() {
        return Score.strike();
    }
}
