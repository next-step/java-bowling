package domain.frame.result;

import domain.Score;
import domain.frame.FirstBowlFrame;
import domain.frame.Frame;

/**
 * Created by hspark on 22/11/2018.
 */
public class Spare implements FrameResult {
	public static final String SPARE_STR = "/";
	private int frameNumber;
	private Score secondScore;

	public Spare(int frameNumber, Score score) {
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
		int nextNumber = getFrameNumber() + 1;
		return nextNumber > MAX_FRAME ? MAX_FRAME : nextNumber;
	}

	@Override
	public FrameResult self() {
		return this;
	}

	@Override
	public String toString() {
		return SPARE_STR;
	}
}
