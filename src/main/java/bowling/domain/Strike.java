package bowling.domain;

public class Strike extends Finished {
    @Override
    public FrameState bowl(FallenPinCount fallenPinCount) {
        return super.bowl(fallenPinCount);
    }
}
