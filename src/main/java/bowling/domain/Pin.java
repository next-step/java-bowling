package bowling.domain;

import java.util.Objects;

public class Pin {
    public static final int MIN_PIN = 0;
    public static final int MAX_PIN = 10;

    private final int fallenPin;

    public Pin(int fallenPin) {
        validateFallenPin(fallenPin);
        this.fallenPin = fallenPin;
    }

    private void validateFallenPin(int fallenPin) {
        if (fallenPin < MIN_PIN || fallenPin > MAX_PIN) {
            throw new IncorrectPinException();
        }
    }

    public int getFallenPin() {
        return fallenPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pin)) return false;
        Pin pin = (Pin) o;
        return fallenPin == pin.fallenPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPin);
    }
}
