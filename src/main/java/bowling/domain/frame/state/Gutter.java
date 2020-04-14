package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class Gutter implements State {
    private static final String EXPRESSION = "-";

    private final Pins first;
    private final Pins second;

    Gutter(final Pins first, final Pins second) {
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
        if (isLeft()) {
            return EXPRESSION + State.DELIMITER + second.count();
        }

        if (isLight()) {
            return first.count() + State.DELIMITER + EXPRESSION;
        }
        return EXPRESSION;
    }

    private boolean isLeft() {
        return first.isGutter() && !second.isGutter();
    }

    private boolean isLight() {
        return !first.isGutter() && second.isGutter();
    }
}
