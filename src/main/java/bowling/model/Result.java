package bowling.model;

import java.util.Objects;

public class Result {
    public static final int MAX_PIN_COUNT = 10;
    public static final Result STRIKE = Result.of(MAX_PIN_COUNT);

    public static final String STRIKE_STRING = "X";
    public static final String GUTTER_STRING = "-";
    public static final String SPARE_STRING = "/";

    private final boolean spare;
    private final int pinCount;

    private Result(int pinCount, boolean spare) {
        if (pinCount < 0 || pinCount > MAX_PIN_COUNT) {
            throw new IllegalArgumentException(String.format("0 ~ %d까지의 범위만 허용됩니다.", MAX_PIN_COUNT));
        }
        if (spare && pinCount == MAX_PIN_COUNT) {
            throw new IllegalArgumentException("스페어와 스트라이크가 동시에 될 수 없습니다.");
        }
        if (spare && pinCount == 0) {
            throw new IllegalArgumentException("스페어는 넘어진 핀의 수가 0이어서는 안됩니다.");
        }
        this.pinCount = pinCount;
        this.spare = spare;
    }

    public static Result of(int pinCount) {
        return new Result(pinCount, false);
    }

    public static Result spare(int pinCount) {
        return new Result(pinCount, true);
    }

    public int get() {
        return pinCount;
    }

    public boolean isSpare() {
        return spare;
    }

    public boolean isStrike() {
        return STRIKE.equals(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return spare == result.spare &&
                pinCount == result.pinCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spare, pinCount);
    }

    @Override
    public String toString() {
        if (pinCount == MAX_PIN_COUNT) {
            return STRIKE_STRING;
        }

        if (pinCount == 0) {
            return GUTTER_STRING;
        }

        if (spare) {
            return SPARE_STRING;
        }
        return Integer.valueOf(pinCount).toString();
    }
}
