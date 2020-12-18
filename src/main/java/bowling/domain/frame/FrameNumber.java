package bowling.domain.frame;

public class FrameNumber {

    private static final int FIRST_FRAME_NUMBER = 0;

    private int value;

    private FrameNumber(int value) {
        validate(value);
        this.value = value;
    }

    public static FrameNumber create(int value) {
        return new FrameNumber(value);
    }

    private void validate(int value) {
        if (value < FIRST_FRAME_NUMBER) {
            throw new IllegalArgumentException("프레임 넘버는 0 보다 작을 수 없습니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
