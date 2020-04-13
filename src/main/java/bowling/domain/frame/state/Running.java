package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class Running implements State{
    private final Pins first;

    public Running(final Pins first) {
        this.first = first;
    }

    @Override
    public State roll(final Pins knockOverPins) {
        Pins second = first.add(knockOverPins);

        if (second.isSpare()) {
            return new Spare(first);
        }

        if (second.isGutter()) {
            return new Gutter();
        }

        return new Miss(first, knockOverPins);
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.FALSE;
    }
}
