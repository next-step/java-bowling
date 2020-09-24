package bowling.step2.domain.status;

import bowling.step2.domain.Pin;

public class Miss extends Finished {
	private final Pin firstPin;
	private final Pin secondPin;

	public Miss(Pin firstPin, Pin secondPin) {
		validStatus(firstPin, secondPin);
		this.firstPin = firstPin;
		this.secondPin = secondPin;
	}

	private void validStatus(Pin firstPin, Pin secondPin) {
		if (firstPin.isStrike() || secondPin.isStrike() || firstPin.isSpare(secondPin)) {
			throw new IllegalArgumentException("입력하신 핀 개수는 " + this.getClass().getSimpleName() + "가 아닙니다.");
		}
	}

	@Override
	public String getMark() {
		return firstPin.toString() + "|" + secondPin.toString();
	}
}
