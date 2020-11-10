package bowling.exception;

public class GameOverException extends RuntimeException {

	public static final String DEFAULT_OVER_MESSAGE = "이미 종료된 게임입니다.";

	public GameOverException() {
		super(DEFAULT_OVER_MESSAGE);
	}
}
