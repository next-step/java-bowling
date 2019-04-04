package domain.state;

import domain.Pins;

public class FirstGutter extends FirstBowlFinished {

    public FirstGutter(int frameNumber, BowlState previous) {
        super(frameNumber, previous);
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%3s", "-");
    }
}