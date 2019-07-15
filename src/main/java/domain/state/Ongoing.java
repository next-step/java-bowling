package domain.state;

import domain.Pins;

import static domain.Pins.EMPTY;

public class Ongoing implements State {

    private Pins first;

    Ongoing(Pins first) {
        this.first = first;
    }

    @Override
    public State bowl(Pins downPins) {
        Pins remainder = first.minus(downPins);
        if (EMPTY.equals(remainder)) {
            return new Spares(first, downPins);
        }
        return new Open(first, downPins);
    }

    @Override
    public Boolean isClosed() {
        return false;
    }
}
