package domain.frame.state;

import domain.Pin;
import domain.Score;
import domain.frame.FrameResult;

/**
 * Created by hspark on 22/11/2018.
 */
public class None implements State {

	@Override
	public State tryBowl(Pin pin) {
		if (pin.equals(Pin.TEN)) {
			return new Strike();
		}
		return new Hit(pin);
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public Score getScore() {
		return Score.of(FrameResult.NON_CALCULATE_SCORE);
	}

	@Override
	public Score calculateScore(Score previousScore) {
		return previousScore;
	}

	@Override
	public String toString() {
		return String.valueOf("");
	}
}
