package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.exception.BowlingException;

public class Miss implements State {

    private static final String PINS_STATE = "%3d|%d ";

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

    @Override
    public String getCurrentPinsState() {
        if (firstPins.isGutter()) {
            return String.format(" -|%d  ", secondPins.getDownPin());
        }

        if (secondPins.isGutter()) {
            return String.format("  %d|- ", secondPins.getDownPin());
        }

        return String.format(PINS_STATE, firstPins.getDownPin(), secondPins.getDownPin());
    }
}
