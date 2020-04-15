package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class Miss implements State {
    private final Pins second;

    Miss(final Pins second) {
        this.second = second;
    }

    @Override
    public State roll(final Pins knockOverPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.TRUE;
    }

    @Override
    public String toResult() {
        if (second.isGutter()) {
            return "-";
        }
        return String.valueOf(second.count());
    }
}
