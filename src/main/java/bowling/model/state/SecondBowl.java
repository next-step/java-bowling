package bowling.model.state;

import bowling.model.Pin;

public class SecondBowl extends Running{
    private Pin firstPins;
    private Pin secondPins;

    SecondBowl(Pin firstPins) {
        this.firstPins = firstPins;
    }

    SecondBowl(Pin firstPins, Pin secondPins) {
        if (!firstPins.isSpare(secondPins)) {
            throw new IllegalArgumentException();
        }

        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public State bowl(int falledPins) {
        Pin secondPins = new Pin(falledPins);
        if (firstPins.isSpare(secondPins)) {
            return new ThirdSpare();
        }

        return new Miss(firstPins, secondPins);
    }
}
