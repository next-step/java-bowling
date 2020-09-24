package bowling.step2.domain.status;

import bowling.step2.domain.Pin;

public class Bonus extends Finished {
	private Pin bonusPin;
	private final String prevMark;

	public Bonus(String statusMark) {
		this.prevMark = statusMark;
	}

	@Override
	public Status bowling(int pin) {
		bonusPin = Pin.bowlPin(pin);
		return this;
	}

	@Override
	public String getMark() {
		return prevMark + "|" + bonusPin.toString();
	}
}
