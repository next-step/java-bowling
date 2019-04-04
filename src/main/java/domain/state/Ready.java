package domain.state;

import domain.Pins;

public class Ready extends BowlState {

    public Ready(int frameNumber, BowlState previous) {
        super(frameNumber, previous);
    }

    @Override
    public BowlState bowl(Pins pins) {
        this.pins = pins;

        if(pins.isNoPinFallen()) {
            next = new FirstGutter(frameNumber, this);
            return next;
        }

        if(pins.areAllPinsFallen()) {
            next = new Strike(frameNumber, this);
            return next;
        }

        next = new FirstBowlFinished(frameNumber, this);
        return next;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public String toString() {
        return "|";
    }
}