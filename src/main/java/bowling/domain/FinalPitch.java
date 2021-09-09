package bowling.domain;

import java.util.Objects;

public class FinalPitch extends Pitch {
    private Pins bonusPins;

    @Override
    public void bowl(Pins pins) {
        if (!isFirstPitchDone()) {
            firstPins = pins;
            score = score.additionalScore(pins);
            return;
        }
        if (!isSecondPitchDone()) {
            secondPins = pins;
            score = score.additionalScore(pins);
            validate();
            return;
        }
        if (!isBonusPitchDone()) {
            score = score.additionalScore(pins);
            bonusPins = pins;
        }
    }

    @Override
    public boolean isFinish() {
        if (isBonusPitchDone()) {
            return true;
        }
        if (isDoubleStrike()) {
            return false;
        }
        if (isSpare()) {
            return false;
        }
        if (isFirstPitchDone() && isSecondPitchDone()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isBonusPitchDone() {
        return Objects.nonNull(bonusPins);
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
        if (isFirstPitchDone() && isSecondPitchDone() && !isBonusPitchDone() && firstPins.isMax() && secondPins.isMax()) {
            return true;
        }
        return false;
    }

    private boolean isStrike() {
        if (isFirstPitchDone() && !isSecondPitchDone() && firstPins.isMax()) {
            return true;
        }
        if (isDoubleStrike()) {
            return true;
        }
        if (isBonusPitchDone() && bonusPins.isMax()) {
            return true;
        }
        return false;
    }

    private boolean isGutter() {
        if (isFirstPitchDone() && !isSecondPitchDone() && firstPins.isMin()) {
            return true;
        }
        if (isFirstPitchDone() && isSecondPitchDone() && secondPins.isMin()) {
            return true;
        }
        if (isBonusPitchDone() && bonusPins.isMin()) {
            return true;
        }
        return false;
    }

    private boolean isMiss() {
        return isFirstPitchDone() && isSecondPitchDone() && firstPins.isMin() && secondPins.isMin();
    }

    private boolean isSpare() {
        return isFirstPitchDone() && isSecondPitchDone() && !isBonusPitchDone() && firstPins.sum(secondPins).isMax();
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
