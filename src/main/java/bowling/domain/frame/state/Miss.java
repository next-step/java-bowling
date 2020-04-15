package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class Miss implements State {
    private static final String DELIMITER = "|";
    private final Pins first;
    private final Pins second;

    Miss(final Pins first, final Pins second) {
        this.first = first;
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
        return first.count() + DELIMITER + second.count();
    }
}
