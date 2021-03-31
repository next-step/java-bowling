package bowling.domain;

public class FrameNumber {

    private static final int MIN_FRAME_NUMBER = 1;

    private static final int MAX_FRAME_NUMBER = 10;

    private final int number;

    public FrameNumber(int number) {
        if (number < MIN_FRAME_NUMBER || number > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException("프레임 번호는 1-10사이어야 합니다.");
        }
        this.number = number;
    }

    public int number() {
        return number;
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
        return number;
    }
}
