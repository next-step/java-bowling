package domain.status;

import domain.pin.Pin;

public class Open extends FrameFinished {
    public Open(Pin first) {
        super(first);
    }

    @Override
    public boolean isClear() {
        return false;
    }

    @Override
    public String toString() {
        return pin.isZeroPin() ? "-" : pin.toString();
    }
}