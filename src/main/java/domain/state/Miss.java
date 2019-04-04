package domain.state;

import domain.Pins;

public class Miss extends BowlState {
    public Miss(int frameNumber, BowlState bowlState) {
        super(frameNumber, bowlState);
    }

    @Override
    public BowlState bowl(Pins pins) {
        return new Ready(frameNumber+1, this);
    }

    @Override
    public int getScore() {
        return pins.getNumber();
    }

    @Override
    public String toString() {
        return String.format("%2d", getScore());
    }
}