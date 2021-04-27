package bowling.domain;

import java.util.Objects;
import java.util.stream.IntStream;

public class RollResult {

    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final RollResult[] CACHE = IntStream.rangeClosed(MIN, MAX)
                                                       .mapToObj(RollResult::new)
                                                       .toArray(RollResult[]::new);

    private final int value;

    private RollResult(int value) {
        this.value = value;
    }

    public static RollResult of(int numberOfPins) {
        validate(numberOfPins);
        return CACHE[numberOfPins];
    }

    private static void validate(int value) {
        if (value < MIN || MAX < value) {
            throw new IllegalArgumentException("쓰러트릴 수 있는 핀의 개수는 0 이상 10 이하 입니다.");
        }
    }

    public boolean isClear() {
        return this.value == MAX;
    }

    public boolean isClearWith(RollResult secondRoll) {
        return this.value + secondRoll.value == MAX;
    }

    public boolean exceedMaximumPins(RollResult secondRoll) {
        return this.value + secondRoll.value > MAX;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RollResult)) return false;
        RollResult that = (RollResult) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
