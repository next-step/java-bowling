package bowling.domain.state.exception;

public class InvalidCreateStateException extends RuntimeException {

	private static final String MESSAGE = "생성할 수 없는 투구 결과";

	public InvalidCreateStateException() {
		super(MESSAGE);
	}
}
