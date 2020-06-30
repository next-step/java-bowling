package bowling.domain.frame;

public class FrameNumber {

    private int value;

    private FrameNumber(int value) {
        validate(value);
        this.value = value;
    }

    public static FrameNumber create(int value) {
        return new FrameNumber(value);
    }

    private void validate(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("프레임 넘버는 0 보다 작을 수 없습니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
