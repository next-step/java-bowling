package bowling;

public class FinalFrame extends Frame {

    public boolean hasThird() {
        return state(1).equals(FrameState.STRIKE.getFrameState()) ||
                state(2).equals(FrameState.SPARE.getFrameState());
    }
}
