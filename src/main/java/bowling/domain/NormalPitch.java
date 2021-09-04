package bowling.domain;

import bowling.exception.IsNotSupportException;

public class NormalPitch extends Pitch {
    @Override
    public void bowl(Pins pins) {
        if (!isFirstPitchDone()) {
            firstPins = pins;
            return;
        }
        if (!isSecondPitchDone()) {
            secondPins = pins;
            validate();
        }
    }

    @Override
    public boolean isFinish() {
        if (!isFirstPitchDone()) {
            return false;
        }
        if (isStrike()) {
            return true;
        }
        if (isSecondPitchDone()) {
            return true;
        }
        return false;
    }

    @Override
    public State getState() {
        if (isStrike()) {
            return State.STRIKE;
        }
        if (isSpare()) {
            return State.SPARE;
        }
        if (isMiss()) {
            return State.MISS;
        }
        if (isGutter()) {
            return State.GUTTER;
        }
        return State.NORMAL;
    }

    public boolean isStrike() {
        return isFirstPitchDone() && !isSecondPitchDone() && firstPins.isMaxPins();
    }

    public boolean isGutter() {
        if (isFirstPitchDone() && !isSecondPitchDone() && firstPins.isMinPins()) {
            return true;
        }
        if (isFirstPitchDone() && isSecondPitchDone() && secondPins.isMinPins()) {
            return true;
        }
        return false;
    }

    public boolean isMiss() {
        return isFirstPitchDone() && isSecondPitchDone() && firstPins.isMinPins() && secondPins.isMinPins();
    }

    public boolean isSpare() {
        return isFirstPitchDone() && isSecondPitchDone() && secondPins.isSumTheMaxPin(firstPins);
    }

    @Override
    public boolean isBonusPitchDone() {
        throw new IsNotSupportException();
    }

    @Override
    public Pins getBonusPin() {
        throw new IsNotSupportException();
    }


}
