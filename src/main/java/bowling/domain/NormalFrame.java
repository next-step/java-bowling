package bowling.domain;

public class NormalFrame implements Frame {

    private PinCount first;
    private PinCount second;

    public NormalFrame() {
        this.first = PinCount.UNDEFINED;
        this.second = PinCount.UNDEFINED;
    }

    @Override
    public void bowl(PinCount fallenPinCount) {
        validateFinishedFrame();
        validateFollowUpPinCount(first, fallenPinCount);
        if (first.isDefined()) {
            second = fallenPinCount;
            return;
        }
        first = fallenPinCount;
    }

    @Override
    public boolean isFinished() {
        return first.isStrike() || (first.isDefined() && second.isDefined());
    }

    @Override
    public Renderer toRenderer() {
        return FrameRenderer.of(first, second);
    }
}
