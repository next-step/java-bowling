package bowling.entity;

import java.util.Objects;

public class NormalScore implements Score {
    private final Pin pin;

    public NormalScore(Pin score) {
        this.pin = score;
    }

    public Pin pin() {
        return pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalScore that = (NormalScore) o;
        return Objects.equals(pin, that.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
