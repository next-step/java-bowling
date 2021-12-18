package bowling.domain.bowl;

import bowling.domain.pin.Pin;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class FinalBowl implements Bowl {

    private static final BowlType BOWL_TYPE = BowlType.FINAL;
    private static final int NONE_LEFT_CHANCE = 0;
    private static final int FIRST_PIN_INDEX = 0;
    private static final int SIZE_OF_ONE_PIN = 1;

    private int leftChance = 2;
    private final List<Pin> pins;

    public FinalBowl() {
        this(new ArrayList<>());
    }

    public FinalBowl(List<Pin> pins) {
        this.pins = pins;
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
        return leftChance > NONE_LEFT_CHANCE;
    }

    private boolean isStrike(Pin pin) {
        return pins.isEmpty() && pin.isAllHit();
    }

    private boolean isSpare(Pin pin) {
        if (pins.size() != SIZE_OF_ONE_PIN) {
            return false;
        }

        Pin firstPin = pins.get(FIRST_PIN_INDEX);
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
    public List<Pin> pins() {
        return unmodifiableList(pins);
    }
}
