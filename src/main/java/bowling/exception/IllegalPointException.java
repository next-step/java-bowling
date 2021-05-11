package bowling.domain.exception;

public class IllegalPointException extends RuntimeException {
    public IllegalPointException() {
        super();
    }

    public IllegalPointException(String message) {
        super(message);
    }
}
