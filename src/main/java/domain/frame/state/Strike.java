package domain.frame.state;

import domain.FrameNumber;
import domain.Pin;
import domain.Score;

/**
 * Created by hspark on 22/11/2018.
 */
public class Strike implements State {
	public static final String STRIKE_STR = "X";

	private FrameNumber frameNumber;

	public Strike(int frameNumber) {
		this.frameNumber = new FrameNumber(frameNumber);
	}

	@Override
	public State tryBowl(Pin pin) {
		throw new IllegalArgumentException();
	}

	@Override
	public int getFrameNumber() {
		return this.frameNumber.toInteger();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public Score getScore() {
		return Score.STRIKE;
	}

	@Override
	public Score calculateScore(Score previousScore) {
		if (previousScore.isScoreCalculateComplete()) {
			return previousScore;
		}
		return previousScore.calculate(Pin.TEN);
	}

	@Override
	public String toString() {
		return STRIKE_STR;
	}
}
