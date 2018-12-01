package bowlinggame.domain.frame.result;

import bowlinggame.domain.frame.Pin;

public class Strike implements Result {

	public static final String RESULT_CHARACTER = "X";
	private static Strike INSTANCE;

	private Strike() {}

	public static Strike getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Strike();
		}
		return INSTANCE;
	}

	@Override
	public Result next(Pin pin) {
		if (pin.isAllKnockedDown()) {
			return Strike.getInstance();
		}
		if (pin.isAllStanding()) {
			return Gutter.getInstance();
		}
		return Miss.getInstance(pin.getKnockedPinCount());
	}

	@Override
	public int getKnockedDownPinCount() {
		return Pin.MAX_COUNT;
	}

	@Override
	public int getBonusCount() {
		return 2;
	}

	@Override
	public String getResult() {
		return RESULT_CHARACTER;
	}
}
