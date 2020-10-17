package bowling.frame.state;

import bowling.score.Pin;

public class Next extends Progress {

    private final Pin previousPins;

    private Next(Pin previousPins) {
        this.previousPins = previousPins;
    }

    public static Next of(Pin previousPins) {
        return new Next(previousPins);
    }

    @Override
    public State bowl(Pin nextPins) {
        if (previousPins.isSpare(nextPins)) {
            return Spare.of(previousPins, nextPins);
        }
        return Miss.of(previousPins, nextPins);
    }

}
