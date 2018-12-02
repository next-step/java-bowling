package domain.frame;

import domain.Pin;
import domain.frame.result.FinalFrameResult;
import domain.frame.result.FrameResult;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by hspark on 22/11/2018.
 */
public class FinalFrame extends Frame {
	private static final int MAX_FRAME = 10;

	private FrameResult frameResult = new FinalFrameResult();

	public FinalFrame() {
		super(MAX_FRAME);
	}

	@Override
	public Frame pitch(Pin pin) {
		checkArgument(!isFinished());
		frameResult.tryBowl(pin);
		return this;
	}

	@Override
	public FrameResult getFrameResult() {
		return this.frameResult;
	}

	@Override
	public boolean isFinished() {
		return frameResult.isFinished();
	}

	@Override
	Frame self() {
		return this;
	}

	@Override
	public int getScore() {
		return frameResult.getScore().getScore();
	}
}
