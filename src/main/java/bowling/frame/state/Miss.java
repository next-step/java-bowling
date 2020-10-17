package bowling.frame.state;

import bowling.score.Pin;

public class Miss extends Done {

    private final Pin previousPins;
    private final Pin currentPins;

    private Miss(Pin previousPins, Pin currentPins) {
        this.previousPins = previousPins;
        this.currentPins = currentPins;
    }

    public static Miss of(Pin previousPins, Pin currentPins) {
        return new Miss(previousPins, currentPins);
    }

    @Override
    public State bowl(Pin fellPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        if (previousPins.isGutter() && currentPins.isGutter()) {
            return "-|-";
        }
        if (previousPins.isGutter()) {
            return "-|" + currentPins;
        }
        if (currentPins.isGutter()) {
            return previousPins.toString() + "|-";
        }
        return previousPins.toString() + "|" + currentPins;
    }
}
