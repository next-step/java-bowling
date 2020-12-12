package bowling.global.exception;

public class OutOfScoreRangeException extends IllegalArgumentException {

    public OutOfScoreRangeException(String message) {
        super(message);
    }

}
