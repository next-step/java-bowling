package bowling.domain;

public class FinalFrame extends Frame {

    private static final int FULL_FRAME = 3;
    private static final int GUARANTEED_OPPORTUNITY = 2;

    public boolean hasThird() {
        return result(1).equals(FrameState.STRIKE.frameState()) ||
                result(2).equals(FrameState.SPARE.frameState());
    }

    @Override
    public boolean hasNext() {
        if (pinNumbers.size() >= FULL_FRAME) {
            return false;
        }
        return pinNumbers.size() < GUARANTEED_OPPORTUNITY || hasThird();
    }

    @Override
    public FrameStrategy nextFrame(int frameNumber) {
        return null;
    }
}
