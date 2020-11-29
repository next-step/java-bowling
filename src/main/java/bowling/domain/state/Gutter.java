package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.last.LastGutter;
import bowling.domain.state.last.LastOrdinary;
import bowling.domain.state.last.LastSpare;

import static bowling.domain.pin.Pin.MAX_PINS;
import static bowling.domain.pin.Pin.MIN_PINS;

public class Gutter implements State {
    private int leftTry = MIN_LEFT_TRY;

    public Gutter() {
    }

    public Gutter(int leftTry) {
        this.leftTry = leftTry;
    }

    @Override
    public State record(int pins) {
        if (pins == MAX_PINS) {
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
        return Score.gutter();
    }
}
