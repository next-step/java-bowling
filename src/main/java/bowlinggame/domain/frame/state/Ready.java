package bowlinggame.domain.frame.state;

import bowlinggame.domain.frame.Pin;
import bowlinggame.domain.frame.Score;

public class Ready implements State {

	private static final String EMPTY_CHARACTER = "";

	@Override
	public State roll(int pinCount) {
		Pin pin = Pin.of(pinCount);
		if (pin.isAllKnockedDown()) {
			return new Strike();
		}
		return new FirstState(pin);
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public Score getScore() {
		return Score.of(-1);
	}

	@Override
	public Score calculateBonus(Score score) {
		return score;
	}

	@Override
	public String getResult() {
		return EMPTY_CHARACTER;
	}
}
