package bowling.domain;

public class FinalFrame extends Frame {

    public boolean hasThird() {
        return state(1).equals(FrameState.STRIKE.frameState()) ||
                state(2).equals(FrameState.SPARE.frameState());
    }

    @Override
    public boolean hasNext() {
        if (pinNumbers.size() >= 3) {
            return false;
        }
        return pinNumbers.size() < 2 || hasThird();
    }

    @Override
    public FrameStrategy nextFrame(int frameNumber) {
        return null;
    }
}
