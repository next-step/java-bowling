package bowling.domain;

public class Spare extends Finished {
    @Override
    public FrameState bowl(PinCount pinCount) {
        return super.bowl(pinCount);
    }
}
