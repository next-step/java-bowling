package bowling.domain.common.exception;

public class InvalidPinCountException extends RuntimeException {

	private static final String MESSAGE = "유효하지 않은 핀 개수";

	public InvalidPinCountException() {
		super(MESSAGE);
	}
}
