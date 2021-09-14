package bowling.domain.state.exception;

public class InvalidProgressPitchException extends RuntimeException {

	private static final String MESSAGE = "진행할 수 없는 투구";

	public InvalidProgressPitchException() {
		super(MESSAGE);
	}
}
