package bowling.state.ended;

import bowling.Pins;
import bowling.state.Throwing;

public class Spare extends Ended {

    @Override
    public Throwing bowl(Pins fallenPins) {
        return null;
    }

    @Override
    public String symbol() {
        return null;
    }
}
