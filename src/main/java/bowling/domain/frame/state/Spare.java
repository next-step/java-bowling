package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class Spare implements State {
    private static final String EXPRESSION = "/";
    private final Pins pins;

    public Spare(final Pins pins) {
        this.pins = pins;
    }

    @Override
    public State roll(final Pins knockOverPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return pins.count() + State.DELIMITER + EXPRESSION;
    }
}
