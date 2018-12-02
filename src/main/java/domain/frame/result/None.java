package domain.frame.result;

import domain.FrameNumber;
import domain.Pin;
import domain.Score;

/**
 * Created by hspark on 22/11/2018.
 */
public class None implements FrameResult {
	private FrameNumber frameNumber;

	public None(int frameNumber) {
		this.frameNumber = new FrameNumber(frameNumber);
	}

	@Override
	public FrameResult tryBowl(Pin pin) {
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
		return Score.of(0);
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
