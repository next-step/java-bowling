package bowling.domain.frame.exception;

public class PitchesFullException extends RuntimeException {

	private static final String MESSAGE = "%d번 이상 투구를 진행할 수 없습니다.";

	public PitchesFullException(final int maxPitchesCount) {
		super(String.format(MESSAGE, maxPitchesCount));
	}
}
