package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.exception.BowlingException;

public class Miss implements State {

    private final Pins firstPins;
    private final Pins secondPins;

    public Miss(final Pins firstPins, final Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public State bowl(int pinsCount) {
        throw new BowlingException(CANT_THROW_BALL);
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
