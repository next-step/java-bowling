package domain.state;

import domain.Pins;

public class Strike extends BowlState {

    public Strike(int frameNumber, BowlState previous) {
        super(frameNumber, previous);
    }

    @Override
    public BowlState bowl(Pins pins) {
        next = new Ready(frameNumber+1, this);
        return next;
    }

    @Override
    public int getScore() {
        return 0;
        //return pins.getNumber() + next.getNext().getPins().getNumber() + next.getNext().getNext().getScore();
    }

    @Override
    public String toString() {
        return "X";
    }
}