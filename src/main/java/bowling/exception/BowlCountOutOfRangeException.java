package bowling.exception;

public class BowlCountOutOfRangeException extends IllegalArgumentException {
    private static final String MESSAGE = "투구수는 0 ~ 10까지 입니다. : %d";

    public BowlCountOutOfRangeException(int causativeValue) {
        super(String.format(MESSAGE, causativeValue));
    }
}
