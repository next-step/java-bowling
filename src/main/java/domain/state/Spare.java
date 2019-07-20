package domain.state;

import domain.Pins;

import static domain.state.Miss.GUTTER_SYMBOL;

public class Spare implements State {
    static final String ALERT_CANNOT_BE_SPARE = "SPARE가 아닙니다.";
    private static final String SPARE_SYMBOL = "/";

    private Pins firstFallenPins;

    Spare(Pins firstFallenPins, Pins secondFallenPins) {
        if (!firstFallenPins.isSpare(secondFallenPins)) {
            throw new IllegalArgumentException(ALERT_CANNOT_BE_SPARE);
        }
        this.firstFallenPins = firstFallenPins;
    }

    @Override
    public State update(Pins fallenPins) {
        throw new ClosedFrameException();
    }

    @Override
    public boolean isClosed() {
        return Boolean.TRUE;
    }

    @Override
    public String printState() {
        String firstPins = firstFallenPins.toString();
        if (firstFallenPins.isMatch(Pins.from(0))) {
            firstPins = GUTTER_SYMBOL;
        }
        return firstPins + "|" + SPARE_SYMBOL;
    }
}
