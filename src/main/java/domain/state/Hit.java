package domain.state;

import domain.Pins;

public class Hit implements State {

    private Pins firstFallenPins;

    Hit(Pins fallenPins) {
        this.firstFallenPins = fallenPins;
    }

    @Override
    public State update(Pins secondFallenPins) {
        if (firstFallenPins.isSpare(secondFallenPins)) {
            return new Spare(firstFallenPins, secondFallenPins);
        }
        return new Miss(firstFallenPins, secondFallenPins);
    }

    @Override
    public boolean isClosed() {
        return Boolean.FALSE;
    }
}
