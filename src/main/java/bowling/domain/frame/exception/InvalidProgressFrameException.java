package bowling.domain.frame.exception;

public class InvalidProgressFrameException extends RuntimeException {

	private static final String MESSAGE = "투구를 진행할 수 없는 프레임입니다.";

	public InvalidProgressFrameException() {
		super(MESSAGE);
	}
}
