package bowling.domain.state.finalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.state.Playing;
import bowling.domain.state.State;

public class FinalFrameFirstState extends Playing {

	public FinalFrameFirstState(DownedPinCount downedPinCount) {
		super(downedPinCount);
	}

	@Override
	public State roll(DownedPinCount downedPinCount) {
		return new FinalFrameSecondState(first, downedPinCount);
	}
}
