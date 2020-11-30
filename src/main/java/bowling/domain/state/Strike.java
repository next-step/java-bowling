package bowling.domain.state;

import bowling.domain.score.Score;

import static bowling.domain.pin.Pin.MAX_PINS;
import static bowling.domain.pin.Pin.MIN_PINS;

public class Strike extends State {
    public Strike(int leftTry) {
        super(leftTry);
    }

    @Override
    public State record(int pins) {
        if (pins == MAX_PINS) {
            return new Strike(leftTry - 1);
        }
        if (pins == MIN_PINS) {
            return new Gutter(leftTry - 1);
        }
        return new Ordinary(pins, leftTry - 1);
    }

    @Override
    public Score getScore() {
        return Score.strike();
    }
}
