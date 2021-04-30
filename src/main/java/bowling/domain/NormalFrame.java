package bowling.domain;

import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateReady;

public class NormalFrame {
    private FrameState currentState = new FrameStateReady();

    public NormalFrame() {

    }

    public NormalFrame(Pinfall pinfall) {
        roll(pinfall);
    }

    public NormalFrame(Pinfall firstPinfall, Pinfall secondPinfall) {
        roll(firstPinfall);
        roll(secondPinfall);
    }

    public FrameResult result() {
        return new FrameResult(currentState.pointSymbols());
    }

    public void roll(Pinfall pinfall) {
        currentState = currentState.roll(pinfall);
    }

    public boolean isDone() {
        return !currentState.isRollable();
    }
}
