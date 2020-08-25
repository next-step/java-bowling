package bowling.domian.frame.exception;

public class ScoreCalculateDoneException extends RuntimeException {

    public ScoreCalculateDoneException() {
    }

    public ScoreCalculateDoneException(String message) {
        super(message);
    }
}
