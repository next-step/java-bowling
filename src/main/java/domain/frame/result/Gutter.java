package domain.frame.result;

import domain.Score;
import domain.frame.FirstBowlFrame;
import domain.frame.Frame;
import domain.frame.SecondBowlFrame;

/**
 * Created by hspark on 22/11/2018.
 */
public class Gutter implements FrameResult {
	public static final String GUTTER_STR = "-";
	private int frameNumber;
	private boolean firstPitching;

	public Gutter(int frameNumber, boolean firstPitching) {
		this.frameNumber = frameNumber;
		this.firstPitching = firstPitching;
	}

	@Override
	public Frame nextGeneralFrame() {
		if (firstPitching) {
			return new SecondBowlFrame(getNextNumber(), Score.ZERO);
		}
		return new FirstBowlFrame(getNextNumber());
	}

	@Override
	public Score getScore() {
		return Score.ZERO;
	}

	@Override
	public int getFrameNumber() {
		return frameNumber;
	}

	@Override
	public int getNextNumber() {
		return firstPitching ? getFrameNumber() : getFrameNumber() + 1;
	}

	@Override
	public FrameResult self() {
		return this;
	}

	@Override
	public String toString() {
		return GUTTER_STR;
	}
}
