package bowling.domain.frame;

import bowling.domain.State.FinalState;
import bowling.domain.State.Ready;
import bowling.domain.State.State;

public class NormalFrame implements Frame {

    private State state;

    private final FrameNumber frameNumber;

    private Frame nextFrame;

    private NormalFrame(FrameNumber frameNumber, State state, Frame nextFrame) {
        this.frameNumber = frameNumber;
        this.state = state;
        this.nextFrame = nextFrame;
    }

    public static NormalFrame first() {
        return new NormalFrame(FrameNumber.first(), new Ready(), null);
    }

    public static NormalFrame of(FrameNumber frameNumber, State state, Frame nextFrame) {
        return new NormalFrame(frameNumber, state, nextFrame);
    }

    public NormalFrame next() {
        NormalFrame next = new NormalFrame(frameNumber.next(), new Ready(), null);
        this.nextFrame = next;
        return next;
    }

    public FinalFrame last() {
        FinalFrame next = FinalFrame.of(frameNumber.next(), new FinalState());
        this.nextFrame = next;
        return next;
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
    public boolean isDone() {
        return state.isClosed();
    }

    @Override
    public Frame nextFrame() {
        return nextFrame;
    }

    @Override
    public FrameNumber number() {
        return frameNumber;
    }

    @Override
    public boolean isLast() {
        return nextFrame == null;
    }

    @Override
    public State currentState() {
        return state;
    }

}
