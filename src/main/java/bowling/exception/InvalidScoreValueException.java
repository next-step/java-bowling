package bowling.exception;

public class InvalidScoreValueException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String INVALID_SCORE = "스코어는 %d ~ %d 의 값이어야 합니다";

    public InvalidScoreValueException(final int min, final int max) {
        super(String.format(INVALID_SCORE, min, max));
    }

}
