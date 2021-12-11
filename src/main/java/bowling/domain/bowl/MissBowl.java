package bowling.domain.bowl;

import bowling.domain.pin.Pin;

public class MissBowl extends FinishedBowl {

    public static final String NOT_MISS_MESSAGE = "미스가 아닙니다.";

    private final Pin firstPin;
    private final Pin secondPin;

    public MissBowl(int firstPin, int secondPin) {
        this(Pin.from(firstPin), Pin.from(secondPin));
    }

    public MissBowl(Pin firstPin, Pin secondPin) {
        checkMiss(firstPin, secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    private void checkMiss(Pin firstPin, Pin secondPin) {
        Pin sumOfPin = firstPin.plus(secondPin);
        if (sumOfPin.isAllHit() || secondPin.isNoneHit()) {
            throw new CanNotPitchException(NOT_MISS_MESSAGE);
        }
    }
}
