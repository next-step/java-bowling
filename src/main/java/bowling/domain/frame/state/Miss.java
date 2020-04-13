package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class Miss implements State {
    private final Pins first;
    private final Pins second;

    public Miss(final Pins first, final Pins second) {
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
    public String toString() {
        return String.valueOf(first.count() + second.count());
    }
}
