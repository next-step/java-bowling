package bowling.domain.pin;

import bowling.exception.PinCountOutOfRangeException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PinCount {

    public static final int MIN_COUNT = 0;
    public static final int MAX_COUNT = 10;
    private static final List<PinCount> COUNTS =
            IntStream.rangeClosed(MIN_COUNT, MAX_COUNT)
                    .boxed()
                    .map(PinCount::new)
                    .collect(Collectors.toList());

    private final int count;

    private PinCount(final int count) {
        validateRange(count);
        this.count = count;
    }

    public static PinCount of(final int count) {
        return COUNTS.get(count);
    }

    private void validateRange(final int count) {
        if (count < MIN_COUNT || count > MAX_COUNT) {
            throw new PinCountOutOfRangeException(count);
        }
    }

    public PinCount sum(final PinCount pinCount) {
        return COUNTS.get(this.count + pinCount.count);
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
