package bowling.domain.state;

import bowling.domain.frame.InvalidFrameRecordActionException;
import bowling.domain.score.Score;
import bowling.domain.state.last.LastGutter;
import bowling.domain.state.last.LastOrdinary;
import bowling.domain.state.last.LastSpare;

import static bowling.domain.pin.Pin.MAX_PINS;
import static bowling.domain.pin.Pin.MIN_PINS;
import static bowling.domain.score.Score.ordinary;

public class Ordinary implements State {
    private final int pins;
    private int leftTry = MIN_LEFT_TRY;

    public Ordinary(int pins) {
        this.pins = pins;
    }

    public Ordinary(int pins, int leftTry) {
        this.pins = pins;
        this.leftTry = leftTry;
    }

    @Override
    public State record(int pins) {
        if (pins == MAX_PINS) {
            throw new InvalidFrameRecordActionException();
        }
        if (this.pins + pins == MAX_PINS) {
            return recordSpare(pins);
        }
        if (pins == MIN_PINS) {
            return new LastGutter();
        }
        return new LastOrdinary(pins);
    }

    private State recordSpare(int pins) {
        if (leftTry == MIN_LEFT_TRY) {
            return new LastSpare(pins);
        }
        return new Spare(pins);
    }

    @Override
    public Score getScore() {
        return ordinary(pins);
    }
}
