package bowling.domain.score.exception;

public class InvalidScoreException extends RuntimeException {

	private static final String MESSAGE = "유효하지 점수";

	public InvalidScoreException() {
		super(MESSAGE);
	}
}
