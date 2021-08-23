package bowling.domain.state;

import bowling.domain.pins.Pins;

public class Ready implements Playing {

    private Ready() {
    }

    public static State of() {
        return new Ready();
    }

    @Override
    public State bowl(Pins pins) {
        if (pins.isStrike()) {
            return Strike.of();
        }
        return FirstBowl.of(pins);
    }
}
