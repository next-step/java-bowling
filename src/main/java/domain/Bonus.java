package domain;

import static domain.Pin.MIN_PINS;

public class Bonus extends State {
    private final String STATE_NAME = "Bonus";

    private int countOfPins;

    @Override
    State bowl(int countOfPin) {
        return null;
    }

    @Override
    int getFellPins() {
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
