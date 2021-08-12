package bowling.exception;

public class InvalidPlayerIndexException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "유효하지 않은 플레이어 인덱스 입니다.";

    public InvalidPlayerIndexException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidPlayerIndexException(String message) {
        super(message);
    }
}
