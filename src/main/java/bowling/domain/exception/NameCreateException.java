package bowling.domain.exception;

public class NameCreateException extends RuntimeException {

	private static final String MESSAGE = "유효하지 않은 이름";

	public NameCreateException() {
		super(MESSAGE);
	}
}
