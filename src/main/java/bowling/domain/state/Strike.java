package bowling.domain.state;

import bowling.domain.Score;

import static bowling.domain.state.Pins.MAX_PINS;

public class Strike extends Result {

    public static final int LEFT = 2;

    public Strike(Pins pins) {
        super(pins);
        this.score = new Score(MAX_PINS, LEFT);
    }

    @Override
    public Score addNextScore(Score score) {
        return addNextScore(score, MAX_PINS);
    }
}
