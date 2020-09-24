package bowling.step2.domain.status;

import bowling.step2.domain.Pin;

public class Spare extends Finished {
	private final Pin firstPin;

	public Spare(Pin firstPin) {
		if (firstPin.isStrike()) {
			throw new IllegalArgumentException("스트라이크는 Spare 상태일 수 없습니다.");
		}
		this.firstPin = firstPin;
	}

	@Override
	public String getMark() {
		return firstPin.toString() + "|/";
	}
}
