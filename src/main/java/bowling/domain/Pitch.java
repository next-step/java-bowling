package bowling.domain;

import bowling.exception.OverTheMaxPinsException;

import java.util.Objects;

public abstract class Pitch {
    protected Pins firstPins;
    protected Pins secondPins;
    protected Score score;

    protected Pitch(){
        this.score = Score.of(0, 0);
    }

    protected void validate() {
        if (!firstPins.isMax() && firstPins.isOverMax(secondPins)) {
            throw new OverTheMaxPinsException();
        }
    }

    public boolean isFirstPitchDone() {
        return Objects.nonNull(firstPins);
    }

    public Score getScore(){
        return score;
    }

    public boolean isSecondPitchDone() {
        return Objects.nonNull(secondPins);
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
