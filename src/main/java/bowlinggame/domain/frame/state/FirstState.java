package bowlinggame.domain.frame.state;

import bowlinggame.domain.frame.Pin;
import bowlinggame.domain.frame.Score;

public class FirstState implements State {

	private Pin firstPin;

	public FirstState(Pin firstPin) {
		this.firstPin = firstPin;
	}

	@Override
	public State roll(int pinCount) {
		Pin secondPin = Pin.of(pinCount);
		if (secondPin.isAllKnockedDown(firstPin)) {
			return new Spare(firstPin, secondPin);
		}
		return new Miss(firstPin, secondPin);
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public Score getScore() {
		return firstPin.toScore();
	}

	@Override
	public Score calculateBonus(Score score) {
		return score.calculateBonus(getScore());
	}

	@Override
	public String getResult() {
		return firstPin.getResult();
	}
}
