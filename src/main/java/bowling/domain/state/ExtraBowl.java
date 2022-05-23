package bowling.domain.state;

import bowling.domain.Score;
import bowling.domain.pin.Pin;

public class ExtraBowl extends Finished {

    private final Pin extraPin;

    public ExtraBowl(Pin extraPin) {
        this.extraPin = extraPin;
    }

    @Override
    public Score score() {
        return Score.ofExtra(extraPin.no());
    }

    @Override
    public Score additionalScore(Score beforeScore) {
        return beforeScore.addedScore(extraPin);
    }

    @Override
    public String expression() {
        if (extraPin.isStrike()) {
            return "X";
        }
        return String.valueOf(extraPin.no());
    }
}
