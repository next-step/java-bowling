package bowling.domain;

import static bowling.Constants.*;

public class PinCount {
    private final int pinCount;

    public PinCount(int pinCount) {
        assertFelledPin(pinCount);

        this.pinCount = pinCount;
    }

    private void assertFelledPin(int felledPin) {
        if (felledPin > MAX_FELLED_PIN_COUNT || felledPin < MIN_FELLED_PIN_COUNT) {
            throw new IllegalArgumentException(WRONG_FELLED_PIN);
        }
    }

    public void assertSumFelledPin(int lastFelledPinCount) {
        if (lastFelledPinCount + pinCount > MAX_FELLED_PIN_COUNT) {
            throw new IllegalArgumentException(WRONG_FELLED_PIN);
        }
    }
}
