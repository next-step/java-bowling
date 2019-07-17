package domain.state;

import domain.Pins;

public class Spare implements State {
    static final String ALERT_CANNOT_BE_SPARE = "스페어가 아닙니다.";

    private Pins firstFallenPins;
    private Pins secondFallenPins;

    Spare(Pins firstFallenPins, Pins secondFallenPins) {
        if (!firstFallenPins.isSpare(secondFallenPins)) {
            throw new IllegalArgumentException(ALERT_CANNOT_BE_SPARE);
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
