package bowling.frame.state;

import bowling.score.Pin;

import java.util.Arrays;
import java.util.List;

public class Spare extends Done {

    private static final String MARK_SPARE = "/";

    private final Pin previousPins;
    private final Pin currentPins;

    private Spare(Pin previousPins, Pin currentPins) {
        this.previousPins = previousPins;
        this.currentPins = currentPins;
    }

    public static Spare of(Pin previousPins, Pin nextPins) {
        return new Spare(previousPins, nextPins);
    }

    @Override
    public State bowl(Pin fellPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getBowlResults() {
        return Arrays.asList(previousPins.toString(), MARK_SPARE);
    }

}
