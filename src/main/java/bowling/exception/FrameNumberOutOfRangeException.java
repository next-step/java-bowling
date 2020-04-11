package bowling.exception;

public class FrameNumberOutOfRangeException extends IllegalArgumentException {
    private static final String MESSAGE = "프레임 번호는 1 ~ 10까지의 번호를 가집니다. : %d";

    public FrameNumberOutOfRangeException(int causativeValue) {
        super(String.format(MESSAGE, causativeValue));
    }
}
