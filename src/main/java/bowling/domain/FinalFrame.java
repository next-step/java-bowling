package bowling.domain;

public class FinalFrame extends Frame {

    private static final int CALCULATION_NOT_COMPLETED = -1;

    private static final int FULL_FRAME = 3;
    private static final int GUARANTEED_OPPORTUNITY = 2;

    public boolean hasThird() {
        return result(1).equals(FrameResult.STRIKE.frameResult()) ||
                result(2).equals(FrameResult.SPARE.frameResult());
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

    @Override
    public int score() {
        if (hasNext()) {
            return CALCULATION_NOT_COMPLETED;
        }
        return sum();
    }
}
