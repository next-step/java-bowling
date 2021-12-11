package bowling.domain.frame;

import bowling.domain.pin.Pin;

public class FinalFrame implements Frame {

    private final int number = 10;

    @Override
    public boolean pitch(Pin pin) {
        return false;
    }

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public boolean hasNextFrame() {
        return false;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
