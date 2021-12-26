package bowling.domain.state;

import bowling.domain.Pins;

public class Ready extends RunningState {
    private static final String READY_SYMBOL = "";

    private Ready() {
    }

    public static ThrowingState create() {
        return new Ready();
    }

    @Override
    public ThrowingState bowl(Pins pins) {
        if (pins.isStrike()) {
            return Strike.create();
        }
        return FirstBowl.create(pins);
    }

    @Override
    public String symbol() {
        return READY_SYMBOL;
    }
}
