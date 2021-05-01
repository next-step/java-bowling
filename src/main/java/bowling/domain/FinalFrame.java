package bowling.domain;

import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateFinalReady;

public class FinalFrame {
    private FrameState currentState = new FrameStateFinalReady();

    public FinalFrame() {
    }

    public FinalFrame(FrameNumber frameNumber) {
        if (!frameNumber.equals(new FrameNumber(10))) {
            throw new IllegalArgumentException("프레임번호가 잘못되었습니다");
        }
    }

    public FinalFrame(Pinfall firstPinfall, Pinfall secondPinfall) {
        this();
        roll(firstPinfall);
        roll(secondPinfall);
    }

    public FinalFrame(Pinfall pinfall) {
        this();
        roll(pinfall);
    }

    public FinalFrame roll(Pinfall pinfall) {
        currentState = currentState.roll(pinfall);
        return this;
    }

    public FrameResult result() {
        return new FrameResult(currentState.pointSymbols());
    }

    public boolean isDone() {
        return !currentState.isRollable();
    }

    public FrameNumber frameNumber() {
        return new FrameNumber(10);
    }
}
