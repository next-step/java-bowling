package bowling.domain.frame;

import bowling.domain.Pin;

public class FinalFrame extends Frame {
    @Override
    public Frame bowl(Pin pin) {
        return null;
    }

    @Override
    public Boolean isFinalFrame() {
        return true;
    }

    @Override
    public Frame nextFrame() {
        return null;
    }

    @Override
    public Boolean isFinished() {
        //TODO
        return false;
    }
}
