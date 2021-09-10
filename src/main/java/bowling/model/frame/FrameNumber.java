package bowling.model.frame;

import java.util.Objects;

public class FrameNumber {
    private static final int MIN = 1;
    private static final int MAX = 10;
    private static final int GAP_BETWEEN_CURRENT_AND_NEXT_NUMBER = 1;

    private final int number;

    public FrameNumber(int number) {
        validateRange(number);

        this.number = number;
    }

    private void validateRange(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException(String.format("프레임 번호는 %d 이상 %d 이하이어야 합니다.", MIN, MAX));
        }
    }

    public static FrameNumber initial() {
        return new FrameNumber(MIN);
    }

    public FrameNumber next() {
        return new FrameNumber(next(number));
    }

    public boolean canMakeNext() {
        return next(number) <= MAX;
    }

    public boolean isNextFinal() {
        return next(number) == MAX;
    }

    private int next(int number) {
        return number + GAP_BETWEEN_CURRENT_AND_NEXT_NUMBER;
    }

    public boolean isUnder(int number) {
        return this.number < number;
    }

    public boolean isEqual(int number) {
        return this.number == number;
    }

    public boolean isOver(int number) {
        return this.number > number;
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
