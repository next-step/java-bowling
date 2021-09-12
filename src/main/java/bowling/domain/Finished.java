package bowling.domain;

public abstract class Finished implements FrameState {
    @Override
    public FrameState bowl(FallenPinCount fallenPinCount) {
        throw new IllegalStateException("종료된 프레임입니다.");
    }
}
