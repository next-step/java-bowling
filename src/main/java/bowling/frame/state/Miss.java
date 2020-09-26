package bowling.frame.state;

import bowling.score.Pin;

public class Miss extends Done {

    private Pin previousPins;
    private Pin currentPins;

    private Miss(Pin previousPins, Pin currentPins) {
        this.previousPins = previousPins;
        this.currentPins = currentPins;
    }

    public static Miss of(Pin previousPins, Pin currentPins) {
        return new Miss(previousPins, currentPins);
    }

    @Override
    public State bowl(String falledPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return previousPins + "|" + currentPins;
    }
}
