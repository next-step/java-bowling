package step3.state;

import step3.Pins;
import step3.Score;

public class Miss extends Finished {
    private Pins firstPins;
    private Pins secondPins;

    public Miss(Pins firstOfPin, Pins secondOfPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        return null;
    }
}
