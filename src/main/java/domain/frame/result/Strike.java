package domain.frame.result;

import domain.Score;
import domain.frame.FirstBowlFrame;
import domain.frame.Frame;

/**
 * Created by hspark on 22/11/2018.
 */
public class Strike implements FrameResult {
	public static final String STRIKE_STR = "X";
	private int frameNumber;

	public Strike(int frameNumber) {
		this.frameNumber = frameNumber;
	}

	@Override
	public Frame nextGeneralFrame() {
		return new FirstBowlFrame(getNextNumber());
	}

	@Override
	public Score getScore() {
		return Score.TEN;
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
		return STRIKE_STR;
	}
}
