package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;

import java.util.Objects;

public class PinCount {
    private static final String OUT_OF_RANGE_ERROR_MESSAGE =
            "점수는 %d에서 %d 사이여야 합니다.";
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final PinCount[] cache = new PinCount[11];

    static {
        for (int i = MIN; i <= MAX; i++) {
            cache[i] = new PinCount(i);
        }
    }

    private int count;
    private boolean spare;

    private PinCount(int count, boolean spare) {
        this.count = count;
        this.spare = spare;
    }

    public PinCount(int count) {
        this(count, false);
    }

    public PinCount(PinCount pinCount) {
        this.count = pinCount.count;
        this.spare = pinCount.spare;
    }

    public int add(int pinCount) {
        return this.count + pinCount;
    }

    public boolean isOverOrSameMaxAfterAdd(int pinCount) {
        return add(pinCount) >= MAX;
    }
    public boolean isOverMaxAfterAdd(int pinCount) {
        return add(pinCount) > MAX;
    }

    public boolean isMax() {
        return count == MAX;
    }

    public int getCount() {
        return count;
    }

    public static PinCount empty() {
        return cache[0];
    }

    public boolean isStrike() {
        return count == MAX;
    }

    public boolean isSpare() {
        return spare;
    }

    public boolean isGutter() {
        return count == MIN;
    }

    public PinCount next(int pinCount) {
        if (add(pinCount) == MAX) {
            return new PinCount(pinCount, true);
        }
        return new PinCount(pinCount);
    }

    public static PinCount valueOf(int pinCount) {
        if (pinCount < MIN || pinCount > MAX) {
            throw new OutOfRangeArgumentException(
                    String.format(OUT_OF_RANGE_ERROR_MESSAGE, MIN, MAX));
        }

        return cache[pinCount];
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PinCount pinCount = (PinCount) o;
        return count == pinCount.count &&
                spare == pinCount.spare;
    }

    @Override public int hashCode() {
        return Objects.hash(count, spare);
    }
}
