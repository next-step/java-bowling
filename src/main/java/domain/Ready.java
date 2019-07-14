package domain;

import static domain.Pin.MIN_PINS;

public class Ready extends State {
    private final String STATE_NAME = "Ready";

    @Override
    public State bowl(int countOfPins) {
        if (countOfPins == super.STRIKE) {
            return new Strike();
        }

        return new FirstBowl(countOfPins);
    }

    @Override
    int getFellPins() {
        return MIN_PINS;
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
