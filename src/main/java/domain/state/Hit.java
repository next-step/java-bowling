package domain.state;

import domain.Pins;

public class Hit implements State {

    private Pins firstFallenPins;

    Hit(Pins fallenPins) {
        this.firstFallenPins = fallenPins;
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
