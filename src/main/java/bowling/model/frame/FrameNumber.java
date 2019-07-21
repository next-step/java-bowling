package bowling.model.frame;

import java.util.Objects;

public class FrameNumber implements Comparable<FrameNumber> {

    static final int MIN = 1;
    public static final int MAX = 10;
    private static final int INCREASE_VALUE = 1;

    static final FrameNumber NUMBER_OF_START_FRAME = of(MIN);
    static final FrameNumber NUMBER_OF_FINAL_FRAME = of(MAX);

    private final int number;

    private FrameNumber(int number) {
        this.number = number;
    }

    static FrameNumber of(int number) {
        if (MIN > number || MAX < number) {
            throw new InvalidFrameNumberException();
        }
        return new FrameNumber(number);
    }

    FrameNumber increase() {
        return of(number + INCREASE_VALUE);
    }

    boolean isFinalNumber() {
        return MAX == number;
    }

    FrameNumber self() {
        return this;
    }

    @Override
    public int compareTo(FrameNumber other) {
        return Integer.compare(number, other.number);
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
        return String.valueOf(number);
    }
}
