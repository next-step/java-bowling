package bowling.exception;

public class GameOverException extends RuntimeException {

	private static final String MESSAGE = "모든 프레임이 종료된 상태입니다.";

	public GameOverException() {
		super(MESSAGE);
	}
}
