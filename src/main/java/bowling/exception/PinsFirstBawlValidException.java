package bowling.exception;

@SuppressWarnings("serial")
public class PinsFirstBawlValidException extends IllegalArgumentException {
	public PinsFirstBawlValidException() {
		super("두 Pins 의 합은 10을 넘을수 없습니다.");
	}
}
