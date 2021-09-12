package bowling.domain;

public class Miss extends Finished {
    @Override
    public FrameState bowl(PinCount pinCount) {
        return super.bowl(pinCount);
    }
}
