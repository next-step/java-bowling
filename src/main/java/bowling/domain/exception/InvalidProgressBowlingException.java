package bowling.domain.exception;

public class InvalidProgressBowlingException extends RuntimeException {

	private static final String MESSAGE = "투구를 진행할 수 없는 볼링 게임 입니다.";

	public InvalidProgressBowlingException() {
		super(MESSAGE);
	}
}
