package domain.state;

import domain.Pins;

public class FirstBowlFinished extends BowlState {

    public FirstBowlFinished(int frameNumber, BowlState previous) {
        super(frameNumber, previous);
    }

    @Override
    public BowlState bowl(Pins pins) {
        this.pins = pins;
        if(previous.getPins().areAllPinsFallen(pins)) {
            next = new Spared(frameNumber, this);
            return next;
        }

        next = new Miss(frameNumber, this);
        return next;
    }

    @Override
    public String toString() {
        return String.format(" %2d", getScore()) + "|";
    }

    @Override
    public int getScore() {
        return pins.getNumber();
    }
}