package bowling.domain.pin;

import java.util.Objects;

public class DownedPin {
    private static final int MIN_NUM_OF_DOWNED_PINS = 0;
    private static final int MAX_NUM_OF_DOWNED_PINS = 10;

    private final int numOfDownedPins;

    private DownedPin(int numOfDownedPins) {
        validate(numOfDownedPins);

        this.numOfDownedPins = numOfDownedPins;
    }

    private void validate(int numOfDownedPins) {
        if (numOfDownedPins < MIN_NUM_OF_DOWNED_PINS || numOfDownedPins > MAX_NUM_OF_DOWNED_PINS) {
            throw new IllegalArgumentException(
                    "Number of downed pins should range in " + MIN_NUM_OF_DOWNED_PINS + " ~ " + MAX_NUM_OF_DOWNED_PINS
            );
        }
    }

    public static DownedPin from(int numOfDownedPins) {
        return new DownedPin(numOfDownedPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownedPin downedPin = (DownedPin) o;
        return numOfDownedPins == downedPin.numOfDownedPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfDownedPins);
    }
}
