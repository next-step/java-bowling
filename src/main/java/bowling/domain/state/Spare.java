package bowling.domain.state;

import bowling.domain.Score;

import static bowling.domain.state.Pins.MAX_PINS;

public class Spare extends Result {

    public static final int LEFT = 1;

    public Spare(Pins pins) {
        super(pins);
        this.score = new Score(MAX_PINS, LEFT);
    }

    @Override
    public Score addNextScore(Score score) {
        score = addNextScore(score, pins.getFirstFall());
        return addNextScore(score, pins.getSecondFall());
    }
}
