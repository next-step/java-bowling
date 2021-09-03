package bowling.domain;

import java.util.Objects;

public abstract class State {
    protected Pins firstPins;
    protected Pins secondPins;

    public abstract void bowl(Pins pins);

    public boolean isFirstFitchDone(){
        return !Objects.isNull(firstPins);
    }
    public boolean isSecondFitchDone(){
        return !Objects.isNull(secondPins);
    }
    public Pins getFirstPin() {
        return firstPins;
    }

    public Pins getSecondPin() {
        return secondPins;
    }

    public abstract boolean isFinish();
}
