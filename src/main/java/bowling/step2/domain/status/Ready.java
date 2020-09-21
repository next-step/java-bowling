package bowling.step2.domain.status;

import bowling.step2.domain.Pin;

public class Ready implements Status {

	@Override
	public Status bowling(int pin) {
		Pin firstPin = Pin.bowlPin(pin);
		if (firstPin.isStrike()) {
			return new Strike();
		}
		return new Running(firstPin);
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public String getMark() {
		return "";
	}
}
