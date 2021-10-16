package bowling.model.state;

import bowling.model.Pin;

public class FirstBowl extends Running{
    private final Pin firstPins;

    FirstBowl(int falledPins) {
        this.firstPins = new Pin(falledPins);
    }

    @Override
    public State bowl(int falledPins) {
        Pin secondPins = new Pin(falledPins);
        if (firstPins.isSpare(secondPins)) {
            return new Spare(firstPins, secondPins);
        }

        return new Miss(firstPins, secondPins);
    }
}
