package bowling.domain.state;

import bowling.domain.frame.InvalidFrameRecordActionException;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import static bowling.domain.pin.Pin.MAX_PINS;
import static bowling.domain.pin.Pin.MIN_PINS;
import static bowling.domain.score.Score.ordinary;

public class Ordinary extends State {
    private final int pins;

    public Ordinary(int pins, int leftTry) {
        super(leftTry);
        this.pins = pins;
    }

    public Ordinary(int pins, int leftTry, Scores scores) {
        super(leftTry, scores);
        this.pins = pins;
        addScore();
    }

    @Override
    public State record(int pins) {
        if (pins == MAX_PINS) {
            throw new InvalidFrameRecordActionException();
        }
        if (this.pins + pins == MAX_PINS) {
            return new Spare(pins, leftTry - 1, scores);
        }
        if (pins == MIN_PINS) {
            return new Gutter(MIN_LEFT_TRY, scores);
        }
        return new Ordinary(pins, MIN_LEFT_TRY, scores);
    }

    @Override
    public Score getScore() {
        return ordinary(pins);
    }
}
