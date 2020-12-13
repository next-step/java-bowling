package bowling.domain.frame;

import bowling.domain.FallingPinCount;
import bowling.domain.state.State;
import bowling.domain.state.finalstate.FinalFrameInitState;

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
    public Frame roll(FallingPinCount fallingPinCount) {
        state = state.roll(fallingPinCount);
        return this;
    }

    @Override
    public State state() {
        return state;
    }
}
