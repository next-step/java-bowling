package bowling.exception;

public enum UserExceptionMesssage {
	NAME_EXCEPTION("이름 형식이 맞지않습니다."),
	BOWLING_BOUND("볼링은 0 과 10 사이의 숫자만 가능합니다"),
	CANNOT_CACULATE("계산 할수 없는 값들입니다."),
	FRAME_MAXIMUM_NINE("normal frame 개수는 최대 9개입니다"),
	FINAL_FRAME_MAX_THREE("final frame은 최대 인자가 3개입니다");

	private final String message;

	UserExceptionMesssage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
