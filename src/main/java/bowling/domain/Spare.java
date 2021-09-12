package bowling.domain;

public class Spare extends Finished {
    @Override
    public FrameState bowl(FallenPinCount fallenPinCount) {
        return super.bowl(fallenPinCount);
    }
}
