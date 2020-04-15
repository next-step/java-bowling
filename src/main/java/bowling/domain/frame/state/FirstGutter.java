package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class FirstGutter implements State {
    private static final String expression = "-";

    private final Pins first;

    FirstGutter(final Pins first) {
        this.first = first;
    }

    @Override
    public State roll(final Pins second) {
        Pins knockOverPins = first.add(second);

        if (second.isGutter()) {
            return new SecondGutter();
        }

        if (knockOverPins.isSpare()) {
            return new Spare();
        }

        return new Miss(second);
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.FALSE;
    }

    @Override
    public String toResult() {
        return expression;
    }
}
