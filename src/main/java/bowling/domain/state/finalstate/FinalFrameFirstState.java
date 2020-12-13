package bowling.domain.state.finalstate;

import bowling.domain.FallingPinCount;
import bowling.domain.state.Playing;
import bowling.domain.state.State;

public class FinalFrameFirstState extends Playing {

    public FinalFrameFirstState(FallingPinCount fallingPinCount) {
        super(fallingPinCount);
    }

    @Override
    public State roll(FallingPinCount fallingPinCount) {
        return new FinalFrameSecondState(first, fallingPinCount);
    }
}
