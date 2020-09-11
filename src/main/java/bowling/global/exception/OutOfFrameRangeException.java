package bowling.global.exception;

public class OutOfFrameRangeException extends IndexOutOfBoundsException {

    public OutOfFrameRangeException(String message) {
        super(message);
    }

}
