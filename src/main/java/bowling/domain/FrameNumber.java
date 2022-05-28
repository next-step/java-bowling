package bowling.domain;

public class FrameNumber {
    public static final int MIN_FRAME_NUMBER = 1;
    public static final int MAX_FRAME_NUMBER = 10;
    public static final int ONE = 1;

    private final int value;

    public FrameNumber(int value) {
        validateFrameNumberRange(value);
        this.value = value;
    }

    public static FrameNumber first() {
        return new FrameNumber(MIN_FRAME_NUMBER);
    }

    public static FrameNumber last() {
        return new FrameNumber(MAX_FRAME_NUMBER);
    }

    public FrameNumber next() {
        validateFrameNumberRange(value + ONE);
        return new FrameNumber(value + ONE);
    }

    public boolean isMax() {
        return value == MAX_FRAME_NUMBER;
    }

    public int getValue() {
        return value;
    }

    private void validateFrameNumberRange(int value) {
        if (value < MIN_FRAME_NUMBER || value > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("프레임 번호는 %d에서 %d 범위의 값이어야 합니다. (프레임 번호: %d)", MIN_FRAME_NUMBER,
                            MAX_FRAME_NUMBER, value));
        }
    }
}
