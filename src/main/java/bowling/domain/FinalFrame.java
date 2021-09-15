package bowling.domain;

public class FinalFrame implements Frame {

    private PinCount first;
    private PinCount second;
    private PinCount third;

    public FinalFrame() {
        this(PinCount.UNDEFINED, PinCount.UNDEFINED, PinCount.UNDEFINED);
    }

    public FinalFrame(PinCount first, PinCount second, PinCount third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public void bowl(PinCount fallenPinCount) {
        validateFinishedFrame();
        if (!first.isDefined()) {
            first = fallenPinCount;
            return;
        }
        if (!second.isDefined()) {
            validateFollowUpPinCount(first, fallenPinCount);
            second = fallenPinCount;
            return;
        }
        if (first.isStrike()) {
            validateFollowUpPinCount(second, fallenPinCount);
        }
        third = fallenPinCount;
    }

    @Override
    public boolean isFinished() {
        return third.isDefined() || isMiss();
    }

    private boolean isMiss() {
        return first.isDefined() && second.isDefined() && !first.isStrike() && !second.spare(first);
    }

    @Override
    public Renderer toRenderer() {
        return FrameRenderer.of(first, second, third);
    }
}
