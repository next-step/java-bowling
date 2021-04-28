package bowling.exception;

public class InvalidFrameNumberException extends IllegalArgumentException {
	public InvalidFrameNumberException(String message) {
		super(message);
	}
}
