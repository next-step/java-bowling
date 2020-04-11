package bowling.exception;

public class FrameBowlCountOutOfRangeException extends IllegalArgumentException {
    private static final String MESSAGE = "투구수는 0 ~ 10까지 입니다. : %d";

    public FrameBowlCountOutOfRangeException(int causativeValue) {
        super(String.format(MESSAGE, causativeValue));
    }
}
