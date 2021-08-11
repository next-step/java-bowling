package bowling.exception;

public class InvalidFrameScoreGradeException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "프레임의 상태를 가져올 수 없습니다.";

    public InvalidFrameScoreGradeException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidFrameScoreGradeException(String message) {
        super(message);
    }
}
