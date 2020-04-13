package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class FirstBowl implements State {

    private final Pins first;

    FirstBowl(Pins first) {
        this.first = first;
    }

    @Override
    public State roll(final Pins second) {
        Pins knockOverPins = first.add(second);

        if (knockOverPins.isSpare()) {
            return new Spare(first);
        }

        if (first.isGutter() || second.isGutter()) {
            return new Gutter(first, second);
        }

        return new Miss(first, second);
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.FALSE;
    }

    @Override
    public String toResult() {
        return first.count() + "";
    }
}
