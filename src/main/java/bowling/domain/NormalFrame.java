package bowling.domain;

public class NormalFrame implements Frame {

    private PinCount first;
    private PinCount second;

    public NormalFrame() {
        this(PinCount.UNDEFINED, PinCount.UNDEFINED);
    }

    public NormalFrame(PinCount first, PinCount second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void bowl(PinCount fallenPinCount) {
        validateFinishedFrame();
        validateSecondBowl(fallenPinCount);
        if (first.isDefined()) {
            second = fallenPinCount;
            return;
        }
        first = fallenPinCount;
    }

    private void validateFinishedFrame() {
        if (this.isFinished()) {
            throw new IllegalStateException("종료된 프레임입니다.");
        }
    }

    private void validateSecondBowl(PinCount fallenPinCount) {
        if (first.isDefined() && fallenPinCount.over(first.remainPinCount())) {
            throw new IllegalArgumentException(String.format("쓰러트린 핀의 수가 유효하지 않습니다. first: %s, second: %s", first, fallenPinCount));
        }
    }

    @Override
    public boolean isFinished() {
        return first.isDefined() && second.isDefined();
    }

    @Override
    public Renderer toRenderer() {
        return FrameRenderer.of(first, second);
    }
}
