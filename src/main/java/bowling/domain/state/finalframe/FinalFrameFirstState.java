package bowling.domain.state.finalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;
import bowling.domain.state.Playing;
import bowling.domain.state.State;

public class FinalFrameFirstState extends Playing {

	public FinalFrameFirstState(DownedPinCount downedPinCount, Score accumulated) {
		super(downedPinCount, accumulated);
	}

	@Override
	public State roll(DownedPinCount downedPinCount, Score accumulated) {
		return new FinalFrameSecondState(first, downedPinCount, accumulated);
	}
}
