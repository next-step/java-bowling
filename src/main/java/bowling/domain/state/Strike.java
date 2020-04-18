package bowling.domain.state;

import bowling.domain.PinCount;

public class Strike extends Finished {
    private static final String TEXT = "X";

    @Override
    public State play(PinCount newFelledPin) {
        if (newFelledPin.isMaxPinCount()) {
            return new Strike();
        }
        if (newFelledPin.isMinPinCount()) {
            return new Gutter();
        }

        return new Playing(newFelledPin);
    }

    @Override
    public String getString() {
        return TEXT;
    }
}
