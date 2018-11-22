package domain.frame.result;

import domain.Score;
import domain.frame.Frame;
import domain.frame.SecondBowlFrame;

/**
 * Created by hspark on 22/11/2018.
 */
public class Hit implements FrameResult {
	private int frameNumber;
	private Score firstScore;

	public Hit(int frameNumber, Score firstScore) {
		this.frameNumber = frameNumber;
		this.firstScore = firstScore;
	}

	@Override
	public Frame nextGeneralFrame() {
		return new SecondBowlFrame(frameNumber, firstScore);
	}

	@Override
	public Score getScore() {
		return firstScore;
	}

	@Override
	public int getFrameNumber() {
		return frameNumber;
	}

	@Override
	public int getNextNumber() {
		return getFrameNumber();
	}

	@Override
	public FrameResult self() {
		return this;
	}

	@Override
	public String toString() {
		return String.valueOf(getScore());
	}
}
