package bowling.exception;

@SuppressWarnings("serial")
public class PinsStrikeValidException extends IllegalArgumentException {
	public PinsStrikeValidException() {
		super("스페어는 두 Pins 의 합이 10 이어야 합니다.");
	}
}
