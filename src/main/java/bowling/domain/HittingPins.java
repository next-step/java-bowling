package bowling.domain;

import java.util.Objects;

public class HittingPins {

    private static final String ERROR_PINS_VALUE_MSG = "볼링 핀은 0-10 까지 입니다";
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private final int count;

    public HittingPins(int count) {
        if (!(MIN_PINS <= count && count <= MAX_PINS)) {
            throw new IllegalArgumentException(ERROR_PINS_VALUE_MSG);
        }
        this.count = count;
    }

    public boolean isStrike() {
        return this.count == MAX_PINS;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HittingPins that = (HittingPins) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return "HittingPins{" +
                "countOfPins=" + count +
                '}';
    }

}
