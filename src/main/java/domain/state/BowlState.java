package domain.state;

import domain.Pins;

public abstract class BowlState {
    protected int frameNumber;
    protected Pins pins;
    protected BowlState previous;
    protected BowlState next;

    public BowlState(int frameNumber, BowlState previous) {
        this.frameNumber = frameNumber;
        this.previous = previous;
    }

    public abstract BowlState bowl(Pins pins);
    public abstract int getScore();

    public int getFrameNumber() {
        return frameNumber;
    }

    public Pins getPins() {
        return pins;
    }

    public BowlState getPrevious() {
        return previous;
    }

    public BowlState getNext() {
        return next;
    }
}