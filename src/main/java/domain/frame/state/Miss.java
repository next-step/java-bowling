package domain.frame.state;

import domain.Pin;
import domain.Score;

/**
 * Created by hspark on 22/11/2018.
 */
public class Miss implements State {
	private Pin firstPin;
	private Pin secondPin;

	public Miss(Pin firstPin, Pin secondPin) {
		this.firstPin = firstPin;
		this.secondPin = secondPin;
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public Score getScore() {
		return Score.of(firstPin.add(secondPin).toInteger());
	}

	@Override
	public Score calculateScore(Score previousScore) {
		Score score = previousScore.calculate(firstPin);
		if (!score.isScoreCalculateComplete()) {
			score = score.calculate(secondPin);
		}
		return score;
	}

	@Override
	public State tryBowl(Pin pin) {
		throw new IllegalArgumentException();
	}

	@Override
	public String toString() {
		return String.format("%s|%s", firstPin, secondPin);
	}
}
