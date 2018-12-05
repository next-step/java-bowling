package bowlinggame.domain.frame.state;

import bowlinggame.domain.frame.Pin;
import bowlinggame.domain.frame.Score;

public class Strike extends LastState {

	private static final String RESULT_CHARACTER = "X";

	private Pin pin;

	public Strike() {
		this.pin = Pin.TEN();
	}

	@Override
	public Score getScore() {
		return Pin.TEN().toScore(2);
	}

	@Override
	public Score calculateBonus(Score score) {
		return score.calculateBonus(pin.toScore());
	}

	@Override
	public String getResult() {
		return RESULT_CHARACTER;
	}
}
