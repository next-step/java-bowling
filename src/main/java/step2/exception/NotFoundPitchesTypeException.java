package step2.exception;

public class NotFoundPitchesTypeException extends RuntimeException {
    public NotFoundPitchesTypeException() {
    }

    public NotFoundPitchesTypeException(String message) {
        super(message);
    }
}
