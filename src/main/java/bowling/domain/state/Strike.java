package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.Score;

public class Strike extends Finished {

    public Strike() {
    }

    @Override
    public Score score() {
        return Score.ofStrike();
    }

    @Override
    public Score additionalScore(Score beforeScore) {
        return beforeScore.addedScore(Pin.of(10));
    }

    @Override
    public String expression() {
        return "X";
    }
}
