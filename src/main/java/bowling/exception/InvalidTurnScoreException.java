package bowling.exception;

public class InvalidTurnScoreException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "유효하지 않은 스코어 입니다.";

    public InvalidTurnScoreException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidTurnScoreException(String message) {
        super(message);
    }
}
