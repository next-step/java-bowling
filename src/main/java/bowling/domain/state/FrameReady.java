package bowling.domain.state;

import bowling.domain.score.Score;

import static bowling.domain.frame.Frame.MAX_TRY_COUNT;
import static bowling.domain.pin.Pin.MAX_PINS;
import static bowling.domain.pin.Pin.MIN_PINS;

public class FrameReady extends State {
    public FrameReady(int leftTry) {
        super(leftTry);
    }

    @Override
    public State record(int pins) {
        if (pins == MAX_PINS) {
            return recordStrike();
        }
        if (pins == MIN_PINS) {
            return new Gutter(leftTry - 1);
        }
        return new Ordinary(pins, leftTry - 1);
    }

    private Strike recordStrike() {
        if (leftTry == MAX_TRY_COUNT) {
            return new Strike(MIN_LEFT_TRY);
        }
        return new Strike(leftTry - 1);
    }

    @Override
    public Score getScore() {
        return null;
    }
}
