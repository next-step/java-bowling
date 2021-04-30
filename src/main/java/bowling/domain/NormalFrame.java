package bowling.domain;

import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateReady;

public class NormalFrame {
    FrameState currentState = new FrameStateReady();

    public FrameResult result() {
        return new FrameResult(currentState.pointSymbols());
    }

    public void roll(Pinfall pinfall) {
        currentState = currentState.roll(pinfall);
    }
}
