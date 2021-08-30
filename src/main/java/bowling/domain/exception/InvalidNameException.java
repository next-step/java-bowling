package bowling.domain.exception;

public class InvalidNameException extends RuntimeException {

	private static final String MESSAGE = "유효하지 않은 이름";

	public InvalidNameException() {
		super(MESSAGE);
	}
}
