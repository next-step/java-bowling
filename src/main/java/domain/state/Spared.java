package domain.state;

import domain.Pins;

public class Spared extends BowlState {

    public Spared(int frameNumber, BowlState previous) {
        super(frameNumber, previous);
    }

    @Override
    public BowlState bowl(Pins pins) {
        next = new Ready(frameNumber+1, this);
        return next;
    }

    @Override
    public int getScore() {
        return pins.getNumber() + next.getNext().getScore();
    }

    @Override
    public String toString() {
       return "/";
    }
}