package bowling.domain.state;

import bowling.domain.Pins;

public class Ready implements State {

    private static final State READY = new Ready();

    private static final String EMPTY_SYMBOL = "";

    private Ready() {
    }

    public static State create() {
        return READY;
    }

    @Override
    public State pitch(Pins pins) {
        if (pins.isStrike()) {
            return Strike.create();
        }
        return FirstBowl.create(pins);
    }

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public String getSymbol() {
        return EMPTY_SYMBOL;
    }

}
