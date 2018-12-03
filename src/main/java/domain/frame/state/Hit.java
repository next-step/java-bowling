package domain.frame.state;

import domain.Pin;
import domain.Score;

/**
 * Created by hspark on 22/11/2018.
 */
public class Hit implements State {
	private Pin firstPin;

	public Hit(Pin firstPin) {
		this.firstPin = firstPin;
	}

	@Override
	public State tryBowl(Pin pin) {
		if (firstPin.add(pin).equals(Pin.TEN)) {
			return new Spare(firstPin);
		}
		return new Miss(firstPin, pin);
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public Score getScore() {
		return Score.of(firstPin.toInteger());
	}

	@Override
	public Score calculateScore(Score previousScore) {
		if(previousScore.isScoreCalculateComplete()){
			return previousScore;
		}
		return previousScore.calculate(firstPin);
	}

	@Override
	public String toString() {
		return firstPin.toString();
	}
}
