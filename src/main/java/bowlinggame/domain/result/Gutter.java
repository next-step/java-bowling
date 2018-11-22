package bowlinggame.domain.result;

import bowlinggame.domain.frame.Pin;

public class Gutter implements Result {

	public static final String RESULT_CHARACTER = "-";

	private static Gutter instance;

	private Gutter() {}

	public static Gutter getInstance() {
		if (instance == null) {
			instance = new Gutter();
		}
		return instance;
	}

	@Override
	public Result next(Pin pin) {
		if (pin.sum(Pin.MIN_PIN_COUNT) == Pin.MAX_PIN_COUNT) {
			return Spare.getInstance();
		}
		if (pin.isStandAll()) {
			return instance;
		}
		return Miss.getInstance(pin.getPinCount());
	}

	@Override
	public int getKnockDownPinCount() {
		return Pin.MIN_PIN_COUNT;
	}

	@Override
	public String getResult() {
		return RESULT_CHARACTER;
	}
}
