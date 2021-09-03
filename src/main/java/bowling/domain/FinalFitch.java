package bowling.domain;

import java.util.Objects;

public class FinalFitch extends Fitch {

    private Pins bonusPins;

    @Override
    public void bowl(Pins pins) {
        if (!isFirstFitchDone()) {
            firstPins = pins;
            return;
        }
        if (!isSecondFitchDone()) {
            secondPins = pins;
            return;
        }
        if (!isBonusFitchDone()) {
            bonusPins = pins;
        }
    }

    @Override
    public boolean isFinish() {
        if (isBonusFitchDone()) {
            return true;
        }
        if (isSpare()) {
            return false;
        }
        if (isDoubleStrike()) {
            return false;
        }
        if (isFirstFitchDone() && isSecondFitchDone()) {
            return true;
        }
        return false;
    }

    public boolean isBonusFitchDone() {
        return !Objects.isNull(bonusPins);
    }

    public Pins getBonusPins() {
        return bonusPins;
    }

    @Override
    public State getState() {
        if (isStrike()) {
            return State.STRIKE;
        }
        if (isMiss()) {
            return State.MISS;
        }
        if (isGutter()) {
            return State.GUTTER;
        }
        if (isSpare()) {
            return State.SPARE;
        }

        return State.NORMAL;
    }

    private boolean isDoubleStrike() {
        if (isFirstFitchDone() && isSecondFitchDone() && firstPins.isMaxPins() && secondPins.isMaxPins()) {
            return true;
        }
        return false;
    }


    private boolean isStrike() {
        if (isFirstFitchDone() && !isSecondFitchDone() && firstPins.isMaxPins()) {
            return true;
        }
        if (isDoubleStrike()) {
            return true;
        }
        if (isBonusFitchDone() && bonusPins.isMaxPins()) {
            return true;
        }
        return false;
    }

    private boolean isGutter() {
        if (isFirstFitchDone() && !isSecondFitchDone() && firstPins.isMinPins()) {
            return true;
        }
        if (isFirstFitchDone() && isSecondFitchDone() && secondPins.isMinPins()) {
            return true;
        }
        if (isSpare() && isBonusFitchDone() && bonusPins.isMinPins()) {
            return true;
        }
        return false;
    }

    private boolean isMiss() {
        return isFirstFitchDone() && isSecondFitchDone() && firstPins.isMinPins() && secondPins.isMinPins();
    }

    private boolean isSpare() {
        if (isFirstFitchDone() && isSecondFitchDone() && secondPins.isSumTheMaxPin(firstPins)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFitch that = (FinalFitch) o;
        return Objects.equals(bonusPins, that.bonusPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusPins);
    }
}
