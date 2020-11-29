package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.last.LastGutter;
import bowling.domain.state.last.LastOrdinary;
import bowling.domain.state.last.LastStrike;

import static bowling.domain.pin.Pin.MAX_PINS;
import static bowling.domain.pin.Pin.MIN_PINS;

public class Spare implements State {
    private final int pins;

    public Spare(int pins) {
        this.pins = pins;
    }

    @Override
    public State record(int pins) {
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
        return Score.spare(pins);
    }
}
