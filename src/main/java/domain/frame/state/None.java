package domain.frame.state;

import domain.FrameNumber;
import domain.Pin;
import domain.Score;
import domain.frame.FrameResult;

/**
 * Created by hspark on 22/11/2018.
 */
public class None implements State {
	private FrameNumber frameNumber;

	public None(int frameNumber) {
		this.frameNumber = new FrameNumber(frameNumber);
	}

	@Override
	public State tryBowl(Pin pin) {
		if (pin.equals(Pin.TEN)) {
			return new Strike(getFrameNumber());
		}
		return new Hit(getFrameNumber(), pin);
	}

	@Override
	public int getFrameNumber() {
		return this.frameNumber.toInteger();
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
