package bowling.domain;

import java.util.Objects;

public class Pin {
    private final int fallenPin;

    public Pin(int fallenPin) {
        this.fallenPin = fallenPin;
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
