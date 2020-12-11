package step4.exception;

public class InvalidPitchesException extends RuntimeException {
    public InvalidPitchesException() {
    }

    public InvalidPitchesException(String message) {
        super(message);
    }
}
