package domain.status;

import domain.score.Score;
import domain.pin.Pin;

import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;
import static domain.status.FirstBowlFinished.ZERO_PIN_DISPLAY_STRING;

public class Open extends FrameFinished {
    private Pin second;

    public Open(Pin first, Pin second) {
        super(first);
        this.second = second;

        if (first.getPin() + second.getPin() >= MAXIMUM_PINS ||
            first.getPin() + second.getPin() < MINIMUM_PINS) {
            throw new IllegalArgumentException();
        }
    }

    public int getCurrentPin() {
        return second.getPin();
    }

    @Override
    public boolean isClear() {
        return false;
    }

    @Override
    public String toString() {
        return second.getPin() > 0 ? second.getPin() + "" : ZERO_PIN_DISPLAY_STRING;
    }

    @Override
    public Score getScore() {
        return Score.of(first.getPin() + second.getPin(), 0);
    }
}