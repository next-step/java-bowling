package bowling.domain;

import bowling.exception.IsNotSupportException;

public class NormalPitch extends Pitch {
    @Override
    public void bowl(Pins pins) {
        if (!isFirstPitchDone()) {
            firstPins = pins;
            score = score.additionalScore(pins);
            additionalScore(isStrike(), 2);
            return;
        }
        if (!isSecondPitchDone()) {
            secondPins = pins;
            validate();
            score = score.additionalScore(pins);
            additionalScore(isSpare(), 1);
        }
    }

    private void additionalScore(boolean additional, int left) {
        if (additional) {
            score = Score.of(10, left);
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
        return isFirstPitchDone() && !isSecondPitchDone() && firstPins.isMax();
    }

    public boolean isGutter() {
        if (isFirstPitchDone() && !isSecondPitchDone() && firstPins.isMin()) {
            return true;
        }
        if (isFirstPitchDone() && isSecondPitchDone() && secondPins.isMin()) {
            return true;
        }
        return false;
    }

    public boolean isMiss() {
        return isFirstPitchDone() && isSecondPitchDone() && firstPins.isMin() && secondPins.isMin();
    }

    public boolean isSpare() {
        return isFirstPitchDone() && isSecondPitchDone() && secondPins.sum(firstPins).isMax();
    }

    @Override
    public boolean isBonusPitchDone() {
        return false;
    }

    @Override
    public Pins getBonusPin() {
        throw new IsNotSupportException();
    }


}
