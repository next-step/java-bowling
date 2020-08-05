package bowling.domain.state.exception;

public class RollingPinCountException extends RuntimeException {
    public RollingPinCountException() {
        super();
    }

    public RollingPinCountException(String message) {
        super(message);
    }
}
