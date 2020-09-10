package bowling.global.exception;

public class OutOfPitchRangeException extends IllegalArgumentException {

    public OutOfPitchRangeException(String message) {
        super(message);
    }

}
