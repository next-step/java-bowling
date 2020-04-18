package bowling.domain.state;

import static bowling.Constants.MIN_FELLED_PIN_COUNT;

public class Ready extends NotFinished {

    private static final String TEXT = "";

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

    @Override
    public int getFelledPin() {
        return MIN_FELLED_PIN_COUNT;
    }

}
