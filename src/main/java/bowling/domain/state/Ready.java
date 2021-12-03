package bowling.domain.state;

import bowling.domain.value.Pins;

public class Ready implements State {
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
            return Gutter.of(pins);
        }

        return FirstBowl.of(pins);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String mark() {
        return EMPTY;
    }
}
