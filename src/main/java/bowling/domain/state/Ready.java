package bowling.domain.state;

import bowling.domain.pin.Pin;

public class Ready implements State {

    private Ready() {
    }

    public static Ready of() {
        return new Ready();
    }

    @Override
    public State bowl(Pin felledPin) {
        if (felledPin.isAllFelled()) {
            return Strike.of();
        }
        return Continue.of(felledPin);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

}
