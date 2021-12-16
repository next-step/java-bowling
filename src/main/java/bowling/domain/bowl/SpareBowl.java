package bowling.domain.bowl;

import bowling.domain.pin.Pin;

import static java.lang.String.format;

public class SpareBowl extends FinishedBowl {

    public static final String NOT_SPARE_MESSAGE = "스페어가 아닙니다.";
    private static final BowlType BOWL_TYPE = BowlType.SPARE;

    private final Pin firstPin;
    private final Pin secondPin;

    public SpareBowl(int firstHitCount, int secondHitCount) {
        this(Pin.from(firstHitCount), Pin.from(secondHitCount));
    }

    public SpareBowl(Pin firstPin, Pin secondPin) {
        checkSpare(firstPin, secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    private void checkSpare(Pin firstPin, Pin secondPin) {
        Pin sumOfPin = firstPin.plus(secondPin);
        if (firstPin.isAllHit() || !sumOfPin.isAllHit()) {
            throw new CanNotPitchException(NOT_SPARE_MESSAGE);
        }
    }

    @Override
    public boolean typeEquals(BowlType bowlType) {
        return BOWL_TYPE.equals(bowlType);
    }

    @Override
    public String getView() {
        return format(" %s|/  ", firstPin.getHitCount());
    }
}
