package bowling.model.state;

import bowling.CannotBowlException;
import bowling.model.Pin;

public class Miss extends Finished{
    private final Pin firstPins;
    private final Pin secondPins;

    Miss(Pin firstPins, Pin secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public State bowl(int countOfPin) throws CannotBowlException {
        throw new CannotBowlException();
    }
}
