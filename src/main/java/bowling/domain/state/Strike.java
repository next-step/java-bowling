package bowling.domain.state;

import bowling.domain.Score;

import static bowling.domain.state.Pins.MAX_PINS;

public class Strike extends Result {

    public Strike(Pins pins) {
        super(pins);
        this.score = Score.getStrikeScore();
    }

    @Override
    public Score addNextScore(Score score) {
        return addNextScore(score, MAX_PINS);
    }
}
