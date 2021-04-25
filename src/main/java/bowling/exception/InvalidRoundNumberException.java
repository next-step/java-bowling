package bowling.exception;

public final class InvalidRoundNumberException extends BowlingException {

    public static final String INVALID_ROUND_NUMBER = "라운드 번호는 0보다 크고 11보다 작아야 합니다. 입력한 번호: ";

    public InvalidRoundNumberException(int roundNumber) {
        super(INVALID_ROUND_NUMBER + roundNumber);
    }
}
