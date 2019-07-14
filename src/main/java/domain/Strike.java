package domain;

import static domain.Frame.ZERO;
import static domain.Pin.MIN_PINS;

public class Strike extends State {
    private final String STATE_NAME = "Strike";
    private final int STRIKE = 10;

    private int firstPin;
    private int secondPin;

    Strike() {
        firstPin = STRIKE;
        secondPin = ZERO;
    }

    @Override
    State bowl(int countOfPins) {
        return null;
    }

    @Override
    public int getFellPins() {
        return firstPin;
    }

    @Override
    int getFirstPin() {
        return firstPin;
    }

    @Override
    int getSecondPin() {
        return MIN_PINS;
    }

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
