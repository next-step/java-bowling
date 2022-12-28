package bowling.domain.state;

import bowling.domain.Pin;

public class Spare extends Finished {

    public static final String SPARE_MESSAGE = "|/";

    private final Pin firstPin;

    public Spare(Pin firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public String toString() {
        return firstPin + SPARE_MESSAGE;
    }
}
