package bowling.exception;

public class InvalidPlayCountException extends RuntimeException {
	public InvalidPlayCountException(String message) {
		super(message);
	}
}
