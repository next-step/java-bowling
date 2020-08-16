package bowling.domian.frame.exception;

public class InvalidScoreCalculateException extends RuntimeException {

    public InvalidScoreCalculateException() {
    }

    public InvalidScoreCalculateException(String message) {
        super(message);
    }
}
