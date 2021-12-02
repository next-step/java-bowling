package bowling.exception;

@SuppressWarnings("serial")
public class PlayerNameSizeException extends IllegalArgumentException {
	public PlayerNameSizeException() {
		super("플레이어 이름은 영어 3자리만 가능합니다.");
	}
}
