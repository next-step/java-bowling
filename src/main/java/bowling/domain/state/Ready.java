package bowling.domain.state;

import bowling.domain.value.Pins;

public class Ready extends InprogressState {
    private static final String EMPTY = "";

    private Ready() {

    }

    public static State of() {
        return new Ready();
    }

    @Override
    public State pitch(Pins pins) {
        if (pins.isStrikeOrSpare()) {
            return Strike.of();
        }

        if (pins.isGutter()) {
            return FirstGutter.of(pins);
        }

        return FirstBowl.of(pins);
    }

    @Override
    public int calculatePins() {
        return 0;
    }

    @Override
    public String mark() {
        return EMPTY;
    }
}
