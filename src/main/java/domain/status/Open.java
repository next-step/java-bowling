package domain.status;

import domain.pin.Pin;

import static domain.status.FirstBowlFinished.ZERO_PIN_DISPLAY_STRING;

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
        return pin.isZeroPin() ? ZERO_PIN_DISPLAY_STRING : pin.toString();
    }
}