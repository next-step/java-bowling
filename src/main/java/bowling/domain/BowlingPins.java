package bowling.domain;

import java.util.Objects;

public class BowlingPins {

    public static final String ERROR_PINS_VALUE_MSG = "볼링 핀은 1-10 까지 입니다";
    private final int count;

    public BowlingPins(int count) {
        if (!(1 <= count && count <= 10)) {
            throw new IllegalArgumentException(ERROR_PINS_VALUE_MSG);
        }
        this.count = count;
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
        BowlingPins that = (BowlingPins) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return "BowlingPins{" +
                "countOfPins=" + count +
                '}';
    }

}
