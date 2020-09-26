package bowling.global.exception;

public class OutOfScoreRangeException extends IndexOutOfBoundsException {

    public OutOfScoreRangeException(String message) {
        super(message);
    }

}
