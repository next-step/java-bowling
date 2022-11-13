package bowling;

public class FinalFrame implements Frame {

    @Override
    public Frame bowl(Pin falledPins) {
        return null;
    }

    @Override
    public Frame nextFrame(Pin falledPins) {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
