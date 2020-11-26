package step2.exception;

public class NotRemainingPitchesCountException extends RuntimeException {
    public NotRemainingPitchesCountException() {
    }

    public NotRemainingPitchesCountException(String message) {
        super(message);
    }
}
