package bowling.model.state;

import bowling.model.Pin;

public class Strike extends Finished{
    private final Pin firstPins;

    Strike() {
        this.firstPins = new Pin(10);
    }

    @Override
    public State bowl(int falledPins) {
        Pin secondPins = new Pin(falledPins);

        if (secondPins.isStrike()) {
            return new SecondStrike();
        }

        return new SecondBowl(firstPins, secondPins);
    }
}
