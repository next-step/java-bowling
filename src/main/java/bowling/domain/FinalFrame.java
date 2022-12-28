package bowling.domain;

public class FinalFrame implements Frame {

    public final int frameNumber = 10;

    @Override
    public void bowl(Pin pin) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Frame nextFrame() {
        return null;
    }

    @Override
    public int frameNumber() {
        return frameNumber;
    }
}
