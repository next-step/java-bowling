package bowling.domain;

import java.util.Objects;

import static bowling.common.ExceptionMessage.PIN_BOWL_RANGE;

public class Pin {

    private final int MAXIMUM_PIN_COUNT = 10;
    private final int MINIMUM_PIN_COUNT = 0;

    private int pins;

    public Pin(int pins) {
        this.pins = pins;
        validationPinCount();
    }

    public int count() {
        return pins;
    }

    private void validationPinCount() {
        if (pins > MAXIMUM_PIN_COUNT || pins < MINIMUM_PIN_COUNT) {
            throw new IllegalArgumentException(PIN_BOWL_RANGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return pins == pin.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
