package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import static bowling.domain.pin.Pin.MAX_PINS;
import static bowling.domain.pin.Pin.MIN_PINS;

public class Gutter extends State {
    public Gutter(int leftTry) {
        super(leftTry);
    }

    public Gutter(int leftTry, Scores scores) {
        super(leftTry, scores);
        addScore();
    }

    @Override
    public State record(int pins) {
        if (pins == MAX_PINS) {
            return new Spare(pins, leftTry - 1, scores);
        }
        if (pins == MIN_PINS) {
            return new Gutter(MIN_LEFT_TRY, scores);
        }
        return new Ordinary(pins, MIN_LEFT_TRY, scores);
    }

    @Override
    public Score getScore() {
        return Score.gutter();
    }
}
