package bowlinggame.domain.result;

import bowlinggame.domain.frame.Pin;

public interface Result {

	static Result first(Pin pin) {
		if (pin.isKnockedDownAll()) {
			return Strike.getInstance();
		}
		if (pin.isStandAll()) {
			return Gutter.getInstance();
		}
		return Miss.getInstance(pin.getPinCount());
	}

	Result next(Pin pin);

	int getKnockDownPinCount();

	String getResult();
}
