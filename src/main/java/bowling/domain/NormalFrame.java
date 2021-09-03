package bowling.domain;

import java.util.Objects;

public class NormalFrame implements Frame{
    private static final int FINAL_NORMAL_FRAME_NUMBER = 9;

    private final int frameNumber;

    private NormalState state;
    private Frame next;

    public NormalFrame(int frameNumber){
        this.frameNumber = frameNumber;
        state = new NormalState();
    }

    @Override
    public Frame bowl(Pins pins) {
        state.bowl(pins);
        if(isFinish()){
            return getFrame();
        }
        return this;
    }

    private Frame getFrame() {
        if(frameNumber == FINAL_NORMAL_FRAME_NUMBER){
            next = new FinalFrame();
            return next;
        }
        next = new NormalFrame(frameNumber + 1);
        return next;
    }

    @Override
    public Pins getFirstPin(){
        return state.getFirstPin();
    }

    @Override
    public Pins getSecondPin() {
        return state.getSecondPin();
    }

    @Override
    public boolean isFinish(){
        return state.isFinish();
    }

    @Override
    public int getFrameNumber(){
        return frameNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame frame = (NormalFrame) o;
        return frameNumber == frame.frameNumber && Objects.equals(state, frame.state) && Objects.equals(next, frame.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, state, next);
    }
}
