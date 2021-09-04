package bowling.domain;

import bowling.exception.OverTheMaxPinsException;

import java.util.Objects;

public abstract class Pitch {
    protected Pins firstPins;
    protected Pins secondPins;

    protected void validate() {
        if (!firstPins.isMaxPins() && secondPins.isSumOverTheMaxPin(firstPins)) {
            throw new OverTheMaxPinsException();
        }
    }

    public boolean isFirstPitchDone() {
        return !Objects.isNull(firstPins);
    }

    public boolean isSecondPitchDone() {
        return !Objects.isNull(secondPins);
    }

    public Pins getFirstPin() {
        return firstPins;
    }

    public Pins getSecondPin() {
        return secondPins;
    }

    public abstract Pins getBonusPin();

    public abstract void bowl(Pins pins);

    public abstract boolean isFinish();

    public abstract State getState();

    public abstract boolean isBonusPitchDone();
}
