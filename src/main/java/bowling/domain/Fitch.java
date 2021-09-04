package bowling.domain;

import bowling.exception.OverTheMaxPinsException;

import java.util.Objects;

public abstract class Fitch {
    protected Pins firstPins;
    protected Pins secondPins;

    protected void validate() {
        if (secondPins.isSumOverTheMaxPin(firstPins)) {
            throw new OverTheMaxPinsException();
        }
    }

    public boolean isFirstFitchDone() {
        return !Objects.isNull(firstPins);
    }

    public boolean isSecondFitchDone() {
        return !Objects.isNull(secondPins);
    }

    public Pins getFirstPin() {
        return firstPins;
    }

    public Pins getSecondPin() {
        return secondPins;
    }

    public abstract void bowl(Pins pins);

    public abstract boolean isFinish();

    public abstract State getState();

    public abstract boolean isBonusFitchDone();

    public abstract Pins getBonusPin();
}
