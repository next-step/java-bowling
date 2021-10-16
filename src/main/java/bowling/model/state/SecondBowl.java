package bowling.model.state;

import bowling.model.Pin;

public class SecondBowl extends Running{
    private final Pin firstPins;
    private final Pin secondPins;

    SecondBowl(Pin firstPins, Pin secondPins) {
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
