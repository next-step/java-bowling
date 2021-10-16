package bowling.model.state;

import bowling.model.Pin;

public class Spare extends Finished{
    private final Pin firstPins;
    private final Pin secondPins;

    Spare(Pin firstPins, Pin secondPins) {
        if (!firstPins.isSpare(secondPins)) {
            throw new IllegalArgumentException();
        }

        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public State bowl(int countOfPin) {
        return null;
    }
}
