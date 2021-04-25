package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;

public final class NullPins implements Pins {

    @Override
    public Pin firstPin() {
        return null;
    }

    @Override
    public Pin secondPin() {
        return null;
    }

    @Override
    public FrameStatus frameStatus() {
        return FrameStatus.NONE;
    }
}
