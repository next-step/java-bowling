package domain.state;

import domain.Pins;

public abstract class Gutter extends BowlState {

    public Gutter(int frameNumber, BowlState previous) {
        super(frameNumber, previous);
    }

    public abstract BowlState bowl(Pins pins);

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public String toString() {
        return "-";
    }
}