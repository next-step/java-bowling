package domain;

import static domain.Pin.MAX_PINS;
import static domain.Pin.MIN_PINS;

public class FirstBowl extends State {
    private final String STATE_NAME = "FirstBowl";

    private int countOfPins;

    FirstBowl(int countOfPins) {
        this.countOfPins = countOfPins;
    }

    @Override
    public State bowl(int countOfPins) {
        if (FrameCounter.isFinalFrame()) {
            return new FinalBowl(this.countOfPins, countOfPins);
        }

        if (this.countOfPins + countOfPins == MAX_PINS) {
            return new Spare(this.countOfPins, countOfPins);
        }

        return new Miss(this.countOfPins, countOfPins);
    }

    @Override
    public int getFellPins() {
        return countOfPins;
    }

    @Override
    int getFirstPin() {
        return MIN_PINS;
    }

    @Override
    int getSecondPin() {
        return MIN_PINS;
    }

    @Override
    public boolean isNameOfState(String state) {
        return STATE_NAME.equals(state);
    }

    @Override
    public String getNameOfState() {
        return STATE_NAME;
    }

    @Override
    boolean nowPlaying() {
        return Boolean.TRUE;
    }
}
