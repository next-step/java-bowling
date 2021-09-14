package bowling.domain;

import bowling.FrameStateRenderer;

public class NormalFrame implements Frame {

    private FrameState state;

    public NormalFrame() {
        this.state = new Ready();
    }

    @Override
    public void bowl(PinCount fallenPinCount) {
        state = state.bowl(fallenPinCount);
    }

    @Override
    public boolean isFinished() {
        return state instanceof Finished;
    }

    @Override
    public FrameStateRenderer toFrameStateRenderer() {
        return state.toRenderer();
    }
}
