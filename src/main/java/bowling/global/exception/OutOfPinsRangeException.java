package bowling.global.exception;

public class OutOfPinsRangeException extends IndexOutOfBoundsException {

    public OutOfPinsRangeException(String message) {
        super(message);
    }

}
