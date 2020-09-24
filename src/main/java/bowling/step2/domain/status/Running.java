package bowling.step2.domain.status;

import bowling.step2.domain.Pin;

public class Running implements Status {
	private final Pin firstPin;

	public Running(Pin pin) {
		this.firstPin = pin;
	}

	@Override
	public Status bowling(int pin) {
		Pin secondPin = Pin.bowlPin(pin);
		if (firstPin.isSpare(pin)) {
			return new Spare(firstPin);
		}
		return new Miss(firstPin, secondPin);
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public String getMark() {
		return firstPin.toString();
	}
}
