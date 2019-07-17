package domain.state;

import domain.Pins;

public class Miss implements State {
    static final String ALERT_CANNOT_BE_MISS = "MISS가 아닙니다.";

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
}
