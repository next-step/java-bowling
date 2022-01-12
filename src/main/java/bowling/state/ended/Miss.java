package bowling.state.ended;

import bowling.Pins;
import bowling.state.Throwing;

public class Miss extends Ended {

    @Override
    public Throwing bowl(Pins fallenPins) {
        return null;
    }

    @Override
    public String symbol() {
        return null;
    }
}
