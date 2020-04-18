package bowling.domain.state;

import static bowling.Constants.MAX_FELLED_PIN_COUNT;
import static bowling.Constants.WRONG_FELLED_PIN;

public class Playing extends NotFinished {

    private final int felledPin;

    public Playing(int felledPin) {
        this.felledPin = felledPin;
    }

    @Override
    public State play(int newFelledPin) {
        assertNewFelledPin(newFelledPin);

        if (felledPin + newFelledPin == MAX_FELLED_PIN_COUNT) {
            return new Spare(felledPin, newFelledPin);
        }

        return new Miss(felledPin, newFelledPin);
    }

    @Override
    public String getString() {
        return String.valueOf(felledPin);
    }

    private void assertNewFelledPin(int newFelledPin) {
        if (felledPin + newFelledPin > MAX_FELLED_PIN_COUNT) {
            throw new IllegalArgumentException(WRONG_FELLED_PIN);
        }
    }
}
