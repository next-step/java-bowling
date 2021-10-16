package bowling.model.state;

import bowling.model.Pin;

public class SecondStrike extends Running{
    private final Pin firstPins;

    SecondStrike() {
        this.firstPins = new Pin(10);
    }

    @Override
    public State bowl(int falledPins) {
        Pin secondPins = new Pin(falledPins);

        if (secondPins.isStrike()) {
            return new ThirdStrike();
        }

        return new Miss(firstPins, secondPins);
    }
}
