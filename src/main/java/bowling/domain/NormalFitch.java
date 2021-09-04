package bowling.domain;

public class NormalFitch extends Fitch {
    @Override
    public void bowl(Pins pins) {
        if (!isFirstFitchDone()) {
            firstPins = pins;
            return;
        }
        if (!isSecondFitchDone()) {
            secondPins = pins;
        }
    }

    @Override
    public boolean isFinish() {
        if (!isFirstFitchDone()) {
            return false;
        }
        if (isStrike()) {
            return true;
        }
        if (isSecondFitchDone()) {
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

    private boolean isStrike() {
        return isFirstFitchDone() && !isSecondFitchDone() && firstPins.isMaxPins();
    }

    private boolean isGutter() {
        if (isFirstFitchDone() && !isSecondFitchDone() && firstPins.isMinPins()) {
            return true;
        }
        if (isFirstFitchDone() && isSecondFitchDone() && secondPins.isMinPins()) {
            return true;
        }
        return false;
    }

    private boolean isMiss() {
        return isFirstFitchDone() && isSecondFitchDone() && firstPins.isMinPins() && secondPins.isMinPins();
    }

    private boolean isSpare() {
        return isFirstFitchDone() && isSecondFitchDone() && secondPins.isSumTheMaxPin(firstPins);
    }

    @Override
    public boolean isBonusFitchDone() {
        return false;
    }

    @Override
    public Pins getBonusPin() {
        return null;
    }
}
