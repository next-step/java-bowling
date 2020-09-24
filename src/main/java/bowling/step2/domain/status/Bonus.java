package bowling.step2.domain.status;

import bowling.step2.domain.Pin;

public class Bonus extends Finished {
	private Pin bonusPin;
	private final String prevMark;

	public Bonus(String statusMark) {
		if (!statusMark.contains("X") && !statusMark.contains("/")) {
			throw new IllegalArgumentException("보너스 투구는 스트라이크나 스페어일 때만 가능합니다.");
		}
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
