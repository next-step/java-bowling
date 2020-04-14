package bowling.exception;

public class BowlCountOverThanPinsException extends IllegalArgumentException {
    private static final String MESSAGE = "서있는 핀 갯수보다 투구수가 많습니다. : %d";

    public BowlCountOverThanPinsException(int causativeValue) {
        super(String.format(MESSAGE, causativeValue));
    }
}
