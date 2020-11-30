package bowling.domain.state;

import bowling.domain.score.Score;

import static bowling.domain.pin.Pin.MAX_PINS;
import static bowling.domain.pin.Pin.MIN_PINS;

public class Gutter extends State {
    public Gutter(int leftTry) {
        super(leftTry);
    }

    @Override
    public State record(int pins) {
        if (pins == MAX_PINS) {
            return new Spare(pins, leftTry - 1);
        }
        if (pins == MIN_PINS) {
            return new Gutter(MIN_LEFT_TRY);
        }
        return new Ordinary(pins, MIN_LEFT_TRY);
    }

    @Override
    public Score getScore() {
        return Score.gutter();
    }
}
