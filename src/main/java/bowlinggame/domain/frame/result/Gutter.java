package bowlinggame.domain.frame.result;

import bowlinggame.domain.frame.Pin;

public class Gutter implements Result {

	public static final String RESULT_CHARACTER = "-";
	private static Gutter INSTANCE;

	private Gutter() {}

	public static Gutter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Gutter();
		}
		return INSTANCE;
	}

	@Override
	public Result next(Pin pin) {
		if (pin.isAllKnockedDown()) {
			return Spare.getInstance(this);
		}
		if (pin.isAllStanding()) {
			return Gutter.getInstance();
		}
		return Miss.getInstance(pin.getKnockedPinCount());
	}

	@Override
	public int getKnockedDownPinCount() {
		return Pin.MIN_COUNT;
	}

	@Override
	public String getResult() {
		return RESULT_CHARACTER;
	}
}
