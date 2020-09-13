package bowling.exception;

public class BowlingException extends RuntimeException {

	public BowlingException(String message) {
		super(message);
	}

	public BowlingException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
