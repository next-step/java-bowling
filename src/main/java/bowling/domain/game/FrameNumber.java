package bowling.domain.game;

import java.util.Objects;

public class FrameNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 10;

    private final int number;

    private FrameNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("유효하지 않은 번호입니다.");
        }
    }

    public static FrameNumber of(int number) {
        return new FrameNumber(number);
    }

    public int getNumber() {
        return number;
    }

    public FrameNumber next() {
        return new FrameNumber(this.number + 1);
    }

    public boolean isEndNumber() {
        return this.number == MAX_NUMBER;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
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
}
