package bowlinggame.domain.frame.state;

import bowlinggame.domain.frame.Pin;
import bowlinggame.domain.frame.Score;

public class Miss extends LastState {

	private Pin firstPin;
	private Pin secondPin;

	public Miss(Pin firstPin, Pin secondPin) {
		this.firstPin = firstPin;
		this.secondPin = secondPin;
	}

	@Override
	public Score getScore() {
		Pin totalPin = firstPin.add(secondPin);
		return totalPin.toScore(0);
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
		return String.format(RESULT_FORMAT, firstPin.getResult(), secondPin.getResult());
	}
}
