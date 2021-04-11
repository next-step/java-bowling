package bowling.domain.frame;

import bowling.domain.State.FinalState;
import bowling.domain.State.State;
import bowling.dto.FinalFrameResult;

public class FinalFrame implements Frame {

    private State state;

    private final FrameNumber frameNumber;

    private FinalFrame(FrameNumber frameNumber, FinalState finalState) {
        this.frameNumber = frameNumber;
        this.state = finalState;
    }

    public static FinalFrame of(FrameNumber frameNumber, FinalState finalState) {
        return new FinalFrame(frameNumber, finalState);
    }

    public static FinalFrame from(int frameNumber) {
        return FinalFrame.of(new FrameNumber(frameNumber), new FinalState());
    }

    @Override
    public void addPinCount(int pinCount) {
        addPinCount(new PinCount(pinCount));
    }

    @Override
    public void addPinCount(PinCount pinCount) {
        state = state.newState(pinCount);
    }

    @Override
    public FrameNumber number() {
        return frameNumber;
    }

    @Override
    public boolean isLast() {
        return true;
    }

    @Override
    public boolean isDone() {
        return state.isClosed();
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임 입니다.");
    }

   // public FinalFrameResult result() {
       // return new FinalFrameResult(frameNumber, frames);
  //  }

    @Override
    public State currentState() {
        return state;
    }

}
