package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.Score;

public class Ready extends Running {

    @Override
    public State bowl(Pin firstPin) {
        if (firstPin.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(firstPin);
    }

    @Override
    public Score additionalScore(Score beforeScore) {
        return beforeScore;
    }

    @Override
    public String expression() {
        return "";
    }
}
