package bowling.domain.state;

import bowling.domain.PinCount;

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
        return String.valueOf(felledPin.getValue());
    }
}
