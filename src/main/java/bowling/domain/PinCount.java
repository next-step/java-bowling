package bowling.domain;

import java.util.Arrays;

public enum PinCount {
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1),
    ZERO(0);

    private final int count;

    PinCount(int count) {
        this.count = count;
    }

    public static PinCount valueOf(int count) {
        return Arrays.stream(values())
                .filter(pinCount -> pinCount.count == count)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("쓰러진 핀의 수는 0에서 10 사이의 값이어야 합니다. count: %s", count)));
    }

    public PinCount leftPinCount() {
        return valueOf(TEN.count - this.count);
    }

    public boolean lessThan(PinCount other) {
        return this.count < other.count;
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}
