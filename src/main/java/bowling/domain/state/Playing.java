package bowling.domain.state;

import bowling.domain.PinCount;

import static bowling.Constants.MAX_FELLED_PIN_COUNT;
import static bowling.Constants.WRONG_FELLED_PIN;

public class Playing extends NotFinished {

    private final PinCount felledPin;

    public Playing(PinCount felledPin) {
        this.felledPin = felledPin;
    }

    @Override
    public State play(PinCount newFelledPin) {
        newFelledPin.assertSumFelledPin(felledPin);

        if (newFelledPin.isMaxPinCountBySum(felledPin)) {
            return new Spare(felledPin, newFelledPin);
        }

        return new Miss(felledPin, newFelledPin);
    }

    @Override
    public String getString() {
        return String.valueOf(felledPin);
    }
}
