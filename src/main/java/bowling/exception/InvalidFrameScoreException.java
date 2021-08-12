package bowling.exception;

public class InvalidFrameScoreException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Frame Score를 가져올 수 없습니다.";

    public InvalidFrameScoreException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidFrameScoreException(String message) {
        super(message);
    }
}
