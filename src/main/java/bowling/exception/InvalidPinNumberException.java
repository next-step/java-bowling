package bowling.exception;

public class InvalidPinNumberException extends IllegalArgumentException {
	public InvalidPinNumberException(String message) {
		super(message);
	}
}
