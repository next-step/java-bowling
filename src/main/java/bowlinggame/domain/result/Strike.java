package bowlinggame.domain.result;

import bowlinggame.domain.frame.Pin;

public class Strike implements Result {

	public static final String RESULT_CHARACTER = "X";

	private static Strike instance;

	private Strike() {}

	public static Strike getInstance() {
		if (instance == null) {
			instance = new Strike();
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
