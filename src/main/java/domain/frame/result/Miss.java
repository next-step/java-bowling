package domain.frame.result;

import domain.Score;
import domain.frame.FirstBowlFrame;
import domain.frame.Frame;

/**
 * Created by hspark on 22/11/2018.
 */
public class Miss implements FrameResult {
	private int frameNumber;
	private Score secondScore;

	public Miss(int frameNumber, Score score) {
		this.frameNumber = frameNumber;
		this.secondScore = score;
	}

	@Override
	public Frame nextGeneralFrame() {
		return new FirstBowlFrame(getNextNumber());
	}

	@Override
	public Score getScore() {
		return secondScore;
	}

	@Override
	public int getFrameNumber() {
		return frameNumber;
	}

	@Override
	public int getNextNumber() {
		return getFrameNumber() + 1;
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
