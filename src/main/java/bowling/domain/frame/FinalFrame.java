package bowling.domain.frame;

import bowling.domain.DownedPinCount;
import bowling.domain.state.finalframe.FinalFrameInitState;
import bowling.domain.state.State;

public class FinalFrame extends Frame {

	private State state;

	public FinalFrame(int index) {
		super(index);
		state = FinalFrameInitState.getInstance();
	}

	@Override
	public boolean isFrameFinished() {
		return state.isDone();
	}

	@Override
	public Frame roll(DownedPinCount downedPinCount) {
		state = state.roll(downedPinCount);
		return this;
	}

	@Override
	public State getState() {
		return state;
	}
}
