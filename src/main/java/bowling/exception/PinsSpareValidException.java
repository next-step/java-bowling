package bowling.exception;

@SuppressWarnings("serial")
public class PinsSpareValidException extends IllegalArgumentException {
	public PinsSpareValidException() {
		super("스페어는 두 Pins 의 합이 10 이어야 합니다.");
	}
}
