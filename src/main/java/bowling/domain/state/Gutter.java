package bowling.domain.state;

import bowling.domain.PinCount;

import static bowling.Constants.MAX_FELLED_PIN_COUNT;
import static bowling.Constants.MIN_FELLED_PIN_COUNT;

public class Gutter extends NotFinished {

    static final String TEXT = "-";

    @Override
    public State play(PinCount newFelledPin) {

        if (newFelledPin.isMaxPinCount()) {
            return new Spare(PinCount.create(MIN_FELLED_PIN_COUNT), newFelledPin);
        }

        return new Miss(PinCount.create(MIN_FELLED_PIN_COUNT), newFelledPin);
    }

    @Override
    public String getString() {
        return TEXT;
    }
}
