package step2.domain.state;

import step2.domain.Pins;
import step2.domain.Score;

public class FirstBowl extends Running {

    public FirstBowl(Pins pins) {

    }

    @Override
    public State bowl(int fallingPins) {
        return null;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return null;
    }
}
