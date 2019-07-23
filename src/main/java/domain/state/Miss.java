package domain.state;

import domain.Pins;

public class Miss implements State {
    static final String ALERT_CANNOT_BE_MISS = "MISS가 아닙니다.";
    static final String GUTTER_SYMBOL = "-";

    private Pins firstFallenPins;
    private Pins secondFallenPins;

    Miss(Pins firstFallenPins, Pins secondFallenPins) {
        if (firstFallenPins.exceedMiss(secondFallenPins)) {
            throw new IllegalArgumentException(ALERT_CANNOT_BE_MISS);
        }
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
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
        String secondPins = secondFallenPins.toString();
        if (firstFallenPins.isMatch(Pins.from(0))) {
            firstPins = GUTTER_SYMBOL;
        }
        if (secondFallenPins.isMatch(Pins.from(0))) {
            secondPins = GUTTER_SYMBOL;
        }
        return firstPins + "|" + secondPins;
    }
}
