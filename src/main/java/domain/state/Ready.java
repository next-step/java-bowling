package domain.state;

import domain.Pins;

public class Ready implements State {

    private Pins first;

    @Override
    public State bowl(Pins downPins) {
        this.first = downPins;
        if (isStrike()) {
            return new Strike(downPins);
        }
        return new Ongoing(first);
    }

    @Override
    public Boolean isClosed() {
        return false;
    }

    private boolean isStrike() {
        return first.equals(Pins.ALL);
    }
}
