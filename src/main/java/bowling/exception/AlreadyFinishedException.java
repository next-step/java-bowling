package bowling.exception;

public class AlreadyFinishedException extends RuntimeException {
    public AlreadyFinishedException() {
        super();
    }

    public AlreadyFinishedException(String message) {
        super(message);
    }

    public AlreadyFinishedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyFinishedException(Throwable cause) {
        super(cause);
    }
}
