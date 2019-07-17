package domain.state;

import domain.Pins;

import static domain.state.Miss.GUTTER_SYMBOL;

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

    @Override
    public String printState() {
        if (firstFallenPins.isMatch(Pins.from(0))) {
            return GUTTER_SYMBOL;
        }
        return firstFallenPins.toString();
    }
}
