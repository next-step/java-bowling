package bowling.frame.state;

import bowling.score.Pin;

public class Next extends Progress {

    private final Pin previousPins;

    private Next(Pin previousPins) {
        this.previousPins = previousPins;
    }

    public static Next bowl(Pin previousPins) {
        return new Next(previousPins);
    }

    @Override
    public State bowl(String falledPins) {
        Pin nextPins = Pin.bowl(falledPins);
        if (previousPins.isSpare(nextPins)) {
            return Spare.of(previousPins, nextPins);
        }
        return Miss.of(previousPins, nextPins);
    }
}
