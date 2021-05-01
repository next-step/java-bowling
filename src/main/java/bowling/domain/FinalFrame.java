package bowling.domain;

import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateFinalReady;

public class FinalFrame implements Frame {
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

    @Override
    public FinalFrame roll(Pinfall pinfall) {
        currentState = currentState.roll(pinfall);
        return this;
    }

    @Override
    public FrameResult result() {
        return new FrameResult(currentState.pointSymbols());
    }

    @Override
    public boolean isDone() {
        return !currentState.isRollable();
    }

    @Override
    public FrameNumber frameNumber() {
        return new FrameNumber(10);
    }

    @Override
    public Frame roll(Pinfall pinfall, FrameFatory frameFatory) {
        return roll(pinfall);
    }
}
