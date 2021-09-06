package bowling.domain.common.exception;

public class InvalidCaculateScoreException extends RuntimeException {

	private static final String MESSAGE = "계산할 수 없는 점수입니다.";

	public InvalidCaculateScoreException() {
		super(MESSAGE);
	}
}
