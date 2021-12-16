package bowling.domain.bowl;

import bowling.domain.pin.Pin;

import java.util.ArrayList;
import java.util.List;

public class FinalBowl implements Bowl {

    private static final BowlType BOWL_TYPE = BowlType.FINAL;

    private int leftChance = 2;
    private final List<Pin> pins;

    public FinalBowl() {
        pins = new ArrayList<>();
    }

    @Override
    public Bowl pitch(Pin pin) {
        checkCanAddPin();

        if (!isStrike(pin) && !isSpare(pin)) {
            leftChance--;
        }
        pins.add(pin);
        return this;
    }

    private void checkCanAddPin() {
        if (!canPitch()) {
            throw new CanNotPitchException();
        }
    }

    @Override
    public boolean canPitch() {
        return leftChance > 0;
    }

    private boolean isStrike(Pin pin) {
        return pins.isEmpty() && pin.isAllHit();
    }

    private boolean isSpare(Pin pin) {
        if (pins.size() != 1) {
            return false;
        }

        Pin firstPin = pins.get(0);
        if (firstPin.isAllHit()) {
            return false;
        }

        Pin sumOfPin = firstPin.plus(pin);
        return sumOfPin.isAllHit();
    }

    @Override
    public boolean typeEquals(BowlType bowlType) {
        return BOWL_TYPE.equals(bowlType);
    }

    @Override
    public String getView() {
        return null;
    }
}
