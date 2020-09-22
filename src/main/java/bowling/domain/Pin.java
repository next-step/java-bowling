package bowling.domain;

import java.util.Objects;

public class Pin {
    public static final int MAX_PIN_COUNT = 10;

    private final int count;

    public Pin(int count) {
        this.validate(count);
        this.count = count;
    }

    private void validate(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("");
        }

        if (count > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("");
        }
    }

    public Pin next(int nextCount) {
        if (this.count + nextCount > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("");
        }

        return new Pin(nextCount);
    }

    public boolean isEnd() {
        return count == MAX_PIN_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return count == pin.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
