package bowling.util;

public enum ErrorTarget {

	PLAYER("플레이어"),
	NEXT_FRAME("다음 프레임");

	private final String message;

	ErrorTarget(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
