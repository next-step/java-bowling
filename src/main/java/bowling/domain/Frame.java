package bowling.domain;

import bowling.FrameStateRenderer;

public class Frame {

    private FrameState state;

    public Frame() {
        this.state = new Ready();
    }

    public void bowl(PinCount fallenPinCount) {
        state = state.bowl(fallenPinCount);
    }

    public boolean isFinished() {
        return state instanceof Finished;
    }

    public FrameStateRenderer toFrameStateRenderer() {
        return state.toRenderer();
    }
}
