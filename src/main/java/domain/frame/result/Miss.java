package domain.frame.result;

import domain.FrameNumber;
import domain.Pin;
import domain.Score;

/**
 * Created by hspark on 22/11/2018.
 */
public class Miss implements FrameResult {
	private FrameNumber frameNumber;
	private Pin firstPin;
	private Pin secondPin;

	public Miss(int frameNumber, Pin firstPin, Pin secondPin) {
		this.firstPin = firstPin;
		this.secondPin = secondPin;
		this.frameNumber = new FrameNumber(frameNumber);
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
		return Score.of(firstPin.add(secondPin).toInteger());
	}

	@Override
	public Score calculateScore(Score previousScore) {
		Score score = previousScore.calculate(firstPin);
		if (!previousScore.isScoreCalculateComplete()) {
			score = score.calculate(secondPin);
		}
		return score;
	}

	@Override
	public FrameResult tryBowl(Pin pin) {
		throw new IllegalArgumentException();
	}

	@Override
	public String toString() {
		return String.format("%s|%s", firstPin, secondPin);
	}
}
