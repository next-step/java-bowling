package bowling.domain;

import java.util.Objects;

public class FinalPitch extends Pitch {
    private Pins bonusPins;

    @Override
    public void bowl(Pins pins) {
        if (!isFirstPitchDone()) {
            firstPins = pins;
            return;
        }
        if (!isSecondPitchDone()) {
            secondPins = pins;
            validate();
            return;
        }
        if (!isBonusPitchDone()) {
            bonusPins = pins;
        }
    }

    @Override
    public boolean isFinish() {
        if (isBonusPitchDone()) {
            return true;
        }
        if (isSpare()) {
            return false;
        }
        if (isDoubleStrike()) {
            return false;
        }
        if (isFirstPitchDone() && isSecondPitchDone()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isBonusPitchDone() {
        return !Objects.isNull(bonusPins);
    }

    @Override
    public Pins getBonusPin() {
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
        if (isFirstPitchDone() && isSecondPitchDone() && !isBonusPitchDone() && firstPins.isMaxPins() && secondPins.isMaxPins()) {
            return true;
        }
        return false;
    }

    private boolean isStrike() {
        if (isFirstPitchDone() && !isSecondPitchDone() && firstPins.isMaxPins()) {
            return true;
        }
        if (isDoubleStrike()) {
            return true;
        }
        if (isBonusPitchDone() && bonusPins.isMaxPins()) {
            return true;
        }
        return false;
    }

    private boolean isGutter() {
        if (isFirstPitchDone() && !isSecondPitchDone() && firstPins.isMinPins()) {
            return true;
        }
        if (isFirstPitchDone() && isSecondPitchDone() && secondPins.isMinPins()) {
            return true;
        }
        if (isBonusPitchDone() && bonusPins.isMinPins()) {
            return true;
        }
        return false;
    }

    private boolean isMiss() {
        return isFirstPitchDone() && isSecondPitchDone() && firstPins.isMinPins() && secondPins.isMinPins();
    }

    private boolean isSpare() {
        return isFirstPitchDone() && isSecondPitchDone() && !isBonusPitchDone() && secondPins.isSumTheMaxPin(firstPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalPitch that = (FinalPitch) o;
        return Objects.equals(bonusPins, that.bonusPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusPins);
    }
}
