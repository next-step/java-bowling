package bowling.domain.frame;

public class FrameNumber {

    private static final int FIRST_FRAME_NUMBER = 0;
    private static final int FINAL_FRAME_NUMBER = 9;

    private final int value;

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

        if (value > FINAL_FRAME_NUMBER) {
            throw new IllegalArgumentException("프레임 넘버는 9 보다 클 수 없습니다.");
        }
    }

    public int getValue() {
        return value;
    }

    public FrameNumber increment() {
        return create(value + 1);
    }
}
