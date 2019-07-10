package bowling.model.frame;

import java.util.Objects;

public class FrameNumber {

    static final int MIN = 1;
    static final int MAX = 10;

    static final FrameNumber NUMBER_OF_START_FRAME = of(MIN);
    static final FrameNumber NUMBER_OF_FINAL_FRAME = of(MAX);

    private final int number;

    private FrameNumber(int number) {
        this.number = number;
    }

    static FrameNumber of(int number) {
        if (MIN > number || MAX < number) {
            throw new IllegalArgumentException();
        }
        return new FrameNumber(number);
    }

    FrameNumber increase() {
        return of(number + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameNumber that = (FrameNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "FrameNumber{" +
                "number=" + number +
                '}';
    }

    public boolean isFinalNumber() {
        return MAX == number;
    }
}
