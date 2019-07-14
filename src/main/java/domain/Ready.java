package domain;

import static domain.Pin.MIN_PINS;

public class Ready extends State {
    private final String STATE_NAME = "Ready";

    @Override
    public State bowl(int countOfPins) {
        if (!FrameCounter.isFinalFrame() && countOfPins == super.STRIKE) {
            return new Strike();
        }

        return new FirstBowl(countOfPins);
    }

    @Override
    int getPoints() {
        return MIN_PINS;
    }

    @Override
    Pin getFirstPin() {
        return null;
    }

    @Override
    Pin getSecondPin() {
        return null;
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
    public String getPoint() {
        return null;
    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    boolean isFrameEnd() {
        return Boolean.FALSE;
    }
}
