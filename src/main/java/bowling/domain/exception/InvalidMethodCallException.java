package bowling.domain.exception;

public class InvalidMethodCallException extends RuntimeException {

	private static final String MESSAGE = "잘못된 메소드 호출입니다.";

	public InvalidMethodCallException() {
		super(MESSAGE);
	}
}
