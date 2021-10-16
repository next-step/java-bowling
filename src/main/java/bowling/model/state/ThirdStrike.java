package bowling.model.state;

import bowling.CannotBowlException;
import bowling.model.Pin;

public class ThirdStrike extends Finished{
    private final Pin firstPins;

    public ThirdStrike() {
        this.firstPins = new Pin(10);
    }

    @Override
    public State bowl(int firstPins) throws CannotBowlException {
        throw new CannotBowlException();
    }
}
