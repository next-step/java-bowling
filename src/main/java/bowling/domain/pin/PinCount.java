package bowling.domain.pin;

import bowling.exception.PinCountOutOfRangeException;

import java.util.Objects;

public class PinCount {

    public static final int MIN_COUNT = 0;
    public static final int MAX_COUNT = 10;

    private final int count;

    private PinCount(final int count) {
        validRange(count);
        this.count = count;
    }

    public static PinCount of(final int count) {
        return new PinCount(count);
    }

    private void validRange(final int count) {
        if (count < MIN_COUNT || count > MAX_COUNT) {
            throw new PinCountOutOfRangeException(count);
        }
    }

    public PinCount increase(final PinCount pinCount) {
        return new PinCount(this.count + pinCount.count);
    }

    public boolean isMaxCount() {
        return this.count == MAX_COUNT;
    }

    public boolean isMinCount() {
        return this.count == MIN_COUNT;
    }

    public boolean isLessThanMaxCount() {
        return this.count < MAX_COUNT;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PinCount)) return false;
        PinCount pinCount = (PinCount) o;
        return count == pinCount.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return Integer.toString(this.count);
    }
}
