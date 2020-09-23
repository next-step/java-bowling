package bowling.domain.frame;

import bowling.domain.DownedPinCount;
import bowling.domain.state.finalframe.FinalFrameInitState;

public class FinalFrame extends Frame {

	public FinalFrame(int index, Frame previous) {
		super(index, previous);
		state = FinalFrameInitState.getInstance();
	}

	@Override
	public Frame roll(DownedPinCount downedPinCount) {
		addScoreToPrevious(downedPinCount);
		state = state.roll(downedPinCount, previous.getScore());
		return this;
	}
}
