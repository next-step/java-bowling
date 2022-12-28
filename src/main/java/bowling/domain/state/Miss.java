package bowling.domain.state;

import bowling.domain.Pin;

public class Miss extends Finished {

    public static final String MISS_MESSAGE = "|";

    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public String toString() {
        return firstPin + MISS_MESSAGE + secondPin;
    }
}
