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
        for(int i = MIN; i <= MAX; i++) {
            cache[i] = new PinCount(i);
        }
    }

    private int pinCount;

    private PinCount(int pinCount) {
        this.pinCount = pinCount;
    }

    public int add(PinCount pinCount) {
        return this.pinCount + pinCount.pinCount;
    }

    public int add(int pinCount) {
        return this.pinCount + pinCount;
    }

    public boolean isOverMaxAfterAdd(PinCount pinCount) {
        return add(pinCount) <= MAX;
    }

    public boolean isMax() {
        return pinCount == MAX;
    }

    public int getPinCount() {
        return pinCount;
    }

    public static PinCount empty() {
        return cache[0];
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
        PinCount pinCount1 = (PinCount) o;
        return pinCount == pinCount1.pinCount;
    }

    @Override public int hashCode() {
        return Objects.hash(pinCount);
    }
}
