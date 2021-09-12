package bowling.domain;

public class Strike extends Finished {
    @Override
    public FrameState bowl(PinCount pinCount) {
        return super.bowl(pinCount);
    }
}
