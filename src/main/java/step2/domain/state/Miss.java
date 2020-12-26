package step2.domain.state;

import step2.domain.Pins;
import step2.domain.Score;

public class Miss extends Finished {

    public Miss(Pins firstPins, Pins pins) {

    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return null;
    }
}
