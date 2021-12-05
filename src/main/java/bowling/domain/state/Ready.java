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

        return FirstBowl.of(pins);
    }

    @Override
    public int countPins() {
        return 0;
    }

    @Override
    public String getMark() {
        return EMPTY;
    }
}
