package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import static bowling.domain.pin.Pin.MAX_PINS;
import static bowling.domain.pin.Pin.MIN_PINS;

public class Spare extends State {
    private final int pins;

    public Spare(int pins, int leftTry) {
        super(leftTry);
        this.pins = pins;
    }

    public Spare(int pins, int leftTry, Scores scores) {
        super(leftTry, scores);
        this.pins = pins;
        addScore();
    }

    @Override
    public State record(int pins) {
        if (pins == MAX_PINS) {
            return new Strike(leftTry - 1, scores);
        }
        if (pins == MIN_PINS) {
            return new Gutter(leftTry - 1, scores);
        }
        return new Ordinary(pins, leftTry - 1, scores);
    }

    @Override
    public Score getScore() {
        return Score.spare(pins);
    }
}
