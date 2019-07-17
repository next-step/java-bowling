package domain.state;

import domain.Pins;

public class Miss implements State {
    public Miss(Pins firstFallenPins, Pins secondFallenPins) {
    }

    @Override
    public State update(Pins fallenPins) {
        return null;
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
