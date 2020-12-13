package bowling.domain.state.finalstate;

import bowling.domain.FallingPinCount;
import bowling.domain.state.InitState;
import bowling.domain.state.State;

public class FinalFrameInitState extends InitState {

    private static final State INIT = new FinalFrameInitState();

    private FinalFrameInitState() {
    }

    public static State getInstance() {
        return INIT;
    }

    @Override
    public State roll(FallingPinCount fallingPinCount) {
        return new FinalFrameFirstState(fallingPinCount);
    }
}
