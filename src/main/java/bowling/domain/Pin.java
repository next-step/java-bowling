package bowling.domain;

import java.util.Objects;

public class Pin {
    private final boolean isKnockOver;

    public Pin(final boolean isKnockOver) {
        this.isKnockOver = isKnockOver;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pin pin = (Pin) o;
        return isKnockOver == pin.isKnockOver;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isKnockOver);
    }
}
