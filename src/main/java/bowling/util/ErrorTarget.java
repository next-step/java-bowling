package bowling.util;

public enum ErrorTarget {

	PlAYER("플레이어");

	private final String message;

	ErrorTarget(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
