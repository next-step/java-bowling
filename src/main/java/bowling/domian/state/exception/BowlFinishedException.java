package bowling.domian.state.exception;

public class BowlFinishedException extends RuntimeException {
    public BowlFinishedException() {
    }

    public BowlFinishedException(String message) {
        super(message);
    }
}
