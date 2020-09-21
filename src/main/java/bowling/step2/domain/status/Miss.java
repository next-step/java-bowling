package bowling.step2.domain.status;

import bowling.step2.domain.Pin;

public class Miss extends Finished {
	private Pin firstPin;
	private Pin secondPin;

	public Miss(Pin firstPin, Pin secondPin) {
		this.firstPin = firstPin;
		this.secondPin = secondPin;
	}

	@Override
	public String getMark() {
		return firstPin.toString() + "|" + secondPin.toString();
	}
}
