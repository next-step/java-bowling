package bowling.domain;

public class FinalFrame extends Frame {

    public boolean hasThird() {
        return state(1).equals(FrameState.STRIKE.frameState()) ||
                state(2).equals(FrameState.SPARE.frameState());
    }
}
