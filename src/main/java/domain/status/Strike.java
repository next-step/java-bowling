package domain.status;

import domain.pin.Pin;

public class Strike extends FrameFinished {
    static final int STRIKE_BONUS_COUNT = 2;
    static final String STRIKE_DISPLAY_STRING = "X";

    @Override
    public int getBonusCount() {
        return STRIKE_BONUS_COUNT;
    }

    @Override
    public Status getNext(Pin pin) {
        return super.getNext(pin);
    }

    @Override
    public boolean isClear() {
        return true;
    }

    @Override
    public String toString() {
        return STRIKE_DISPLAY_STRING;
    }
}