package bowling.frame.state;

import bowling.score.Pin;

import java.util.Arrays;
import java.util.List;

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
    public List<String> getBowlResults() {
        return Arrays.asList(previousPins.toString(), currentPins.toString());
    }

}
