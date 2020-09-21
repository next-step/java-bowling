package bowling.step2.domain.status;

import bowling.step2.domain.Pin;

public class Spare extends Finished {
	private Pin firstPin;

	public Spare(Pin firstPin) {
		this.firstPin = firstPin;
	}

	@Override
	public String getMark() {
		return firstPin.toString() + "|/";
	}
}
