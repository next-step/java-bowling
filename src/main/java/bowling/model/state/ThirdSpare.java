package bowling.model.state;

import bowling.CannotBowlException;
import bowling.model.Pin;

public class ThirdSpare extends Finished {
    private final Pin firstPins;

    public ThirdSpare() {
        this.firstPins = new Pin(10);
    }

    @Override
    public State bowl(int firstPins) throws CannotBowlException {
        throw new CannotBowlException();
    }
}
