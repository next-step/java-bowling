package bowling.domain.state.finalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.state.InitState;
import bowling.domain.state.State;

public class FinalFrameInitState extends InitState {

	private static final State INIT = new FinalFrameInitState();

	private FinalFrameInitState() {}

	public static State getInstance() {
		return INIT;
	}

	@Override
	public State roll(DownedPinCount downedPinCount) {
		return new FinalFrameFirstState(downedPinCount);
	}
}
