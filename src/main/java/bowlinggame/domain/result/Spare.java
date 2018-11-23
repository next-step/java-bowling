package bowlinggame.domain.result;

import bowlinggame.domain.frame.Pin;

public class Spare implements Result {

	public static final String RESULT_CHARACTER = "/";

	private static Spare instance;

	private Spare() {}

	public static Spare getInstance() {
		if (instance == null) {
			instance = new Spare();
		}
		return instance;
	}

	@Override
	public Result next(Pin pin) {
		return this;
	}

	@Override
	public int getKnockDownPinCount() {
		return Pin.MAX_PIN_COUNT;
	}

	@Override
	public String getResult() {
		return RESULT_CHARACTER;
	}
}
