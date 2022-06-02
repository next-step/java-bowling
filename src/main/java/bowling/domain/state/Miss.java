package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Miss extends Finished {
    private Pins firstPins;
    private Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public String expression() {
        return null;
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        return null;
    }
}
