package bowling.exception;

public class InvalidPlayerNameException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "유효하지 않은 플레이어 이름의 길이 입니다.";

    public InvalidPlayerNameException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidPlayerNameException(String message) {
        super(message);
    }
}
