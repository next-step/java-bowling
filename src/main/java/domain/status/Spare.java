package domain.status;

import domain.pin.Pin;
import domain.score.Score;

import static domain.pin.Pin.MAXIMUM_PINS;

public class Spare extends FrameFinished {
    static final String SPARE_DISPLAY_STRING = "/";

    private final Pin second;

    public Spare(Pin first, Pin second) {
        super(first);
        this.second = second;

        if (first.getPin() + second.getPin() != MAXIMUM_PINS) {
            throw new IllegalArgumentException();
        }
    }

    public int getCurrentPin() {
        return second.getPin();
    }

    @Override
    public boolean isClear() {
        return true;
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }

    @Override
    public String toString() {
       return SPARE_DISPLAY_STRING;
    }
}