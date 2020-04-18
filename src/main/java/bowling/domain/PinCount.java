package bowling.domain;

import static bowling.Constants.*;

public class PinCount {
    private final int pinCount;

    public PinCount(int pinCount) {
        assertFelledPin(pinCount);

        this.pinCount = pinCount;
    }

    public static PinCount create(int pinCount) {
        return new PinCount(pinCount);
    }

    private void assertFelledPin(int felledPin) {
        if (felledPin > MAX_FELLED_PIN_COUNT || felledPin < MIN_FELLED_PIN_COUNT) {
            throw new IllegalArgumentException(WRONG_FELLED_PIN);
        }
    }

    public void assertSumFelledPin(PinCount lastFelledPinCount) {
        if (lastFelledPinCount.getValue() + pinCount > MAX_FELLED_PIN_COUNT) {
            throw new IllegalArgumentException(WRONG_FELLED_PIN);
        }
    }

    public boolean isMaxPinCount() {
        return pinCount == MAX_FELLED_PIN_COUNT;
    }

    public boolean isMaxPinCountBySum(PinCount lastFelledPinCount) {
        return pinCount + lastFelledPinCount.getValue() == MAX_FELLED_PIN_COUNT;
    }

    public boolean isMinPinCount() {
        return pinCount == MIN_FELLED_PIN_COUNT;
    }

    public int getValue() {
        return pinCount;
    }
}
