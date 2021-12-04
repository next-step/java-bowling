package bowling.exception;

@SuppressWarnings("serial")
public class CannotCalculateException extends IllegalArgumentException {
	public CannotCalculateException() {
		super("점수를 계산할 수 없는 상태입니다.");
	}
}
