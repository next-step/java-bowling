package bowling.global.exception;

public class ScoreMaxSizeException extends IllegalArgumentException {

    public ScoreMaxSizeException(String message) {
        super(message);
    }
}
