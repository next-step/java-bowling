package bowling.domain;

import java.util.Objects;

public class NumberOfHitPin {
    private final int value;

    public NumberOfHitPin(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberOfHitPin that = (NumberOfHitPin) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
