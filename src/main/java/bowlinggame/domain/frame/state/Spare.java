package bowlinggame.domain.frame.state;

import bowlinggame.domain.frame.Pin;
import bowlinggame.domain.frame.Score;

public class Spare extends LastState {

	public static final String RESULT_CHARACTER = "/";

	private Pin firstPin;
	private Pin secondPin;

	public Spare(Pin firstPin, Pin secondPin) {
		this.firstPin = firstPin;
		this.secondPin = secondPin;
	}

	@Override
	public Score getScore() {
		return Pin.TEN().toScore(1);
	}

	@Override
	public Score calculateBonus(Score score) {
		score = score.calculateBonus(firstPin.toScore());
		if (score.hasBonus()) {
			score = score.calculateBonus(secondPin.toScore());
		}
		return score;
	}

	@Override
	public String getResult() {
		return String.format(RESULT_FORMAT, firstPin.getResult(), RESULT_CHARACTER);
	}
}
