package bowling.step2.domain;

import java.util.Arrays;

public enum Count {
    NONE(0),
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10);

    private final int count;

    Count(int count) {
        this.count = count;
    }

    public int value() {
        return count;
    }

    public static Count of(int count) {
        return Arrays.stream(values())
                .filter(count1 -> count1.value() == count)
                .findFirst()
                .orElse(Count.NONE);
    }
}
