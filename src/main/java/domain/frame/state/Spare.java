package domain.frame.state;

import domain.FrameNumber;
import domain.Pin;
import domain.Score;

/**
 * Created by hspark on 22/11/2018.
 */
public class Spare implements State {
	public static final String SPARE_STR = "/";

	private FrameNumber frameNumber;
	private Pin firstPin;
	private Pin secondPin;

	public Spare(int frameNumber, Pin firstPin) {
		this.frameNumber = new FrameNumber(frameNumber);
		this.firstPin = firstPin;
		this.secondPin = Pin.TEN.sub(firstPin);
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
		return Score.SPARE;
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
	public String toString() {
		return String.format("%s|%s", firstPin, SPARE_STR);
	}
}
