package bowling.domain;

public class Miss extends Finished {
    @Override
    public FrameState bowl(FallenPinCount fallenPinCount) {
        return super.bowl(fallenPinCount);
    }
}
