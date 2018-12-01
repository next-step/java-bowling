package bowlinggame.domain.frame.result;

import bowlinggame.domain.frame.Pin;

public interface Result {

	static Result first(Pin pin) {
		if (pin.isAllKnockedDown()) {
			return Strike.getInstance();
		}
		if (pin.isAllStanding()) {
			return Gutter.getInstance();
		}
		return Miss.getInstance(pin.getKnockedPinCount());
	}

	Result next(Pin pin);
	int getKnockedDownPinCount();
	int getBonusCount();
	String getResult();
}
