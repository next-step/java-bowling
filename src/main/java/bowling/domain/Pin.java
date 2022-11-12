package bowling.domain;

import java.util.Objects;

public class Pin {

    private static final int MAX_COUNT = 10;
    private static final int MIN_COUNT = 0;

    private final int count;

    public Pin(final int count) {

        validateRange(count);
        this.count = count;
    }

    private static void validateRange(final int count) {

        if (count < MIN_COUNT || count > MAX_COUNT) {
            throw new IllegalArgumentException(MIN_COUNT + "~" + MAX_COUNT + " 범위의 수만 가능합니다.");
        }
    }

    public int count() {

        return count;
    }

    public boolean isKnockDown() {

        return count == MAX_COUNT;
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