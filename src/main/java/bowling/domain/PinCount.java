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
    private boolean isSpare;

    private PinCount(int count, boolean isSpare) {
        this.count = count;
        this.isSpare = isSpare;
    }

    public PinCount(int count) {
        this(count, false);
    }

    public PinCount(PinCount pinCount) {
        this.count = pinCount.count;
        this.isSpare = pinCount.isSpare;
    }

    public int add(int pinCount) {
        return this.count + pinCount;
    }

    public boolean isOverMaxAfterAdd(int pinCount) {
        return add(pinCount) <= MAX;
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
        return isSpare;
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
                isSpare == pinCount.isSpare;
    }

    @Override public int hashCode() {
        return Objects.hash(count, isSpare);
    }
}
