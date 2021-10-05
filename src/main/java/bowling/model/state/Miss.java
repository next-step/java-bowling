package bowling.model.state;

import java.util.Objects;

import static bowling.controller.Main.stateResult;

import bowling.CannotBowlException;
import bowling.model.Pin;
import bowling.model.State;


public class Miss implements State {
    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public State bowl(int countOfPin) throws CannotBowlException {
        throw new CannotBowlException();
    }

    @Override
    public boolean isFinish(int frameNo) {
        return true;
    }

    @Override
    public String toString() {
        return stateResult.removeLast() + "|" + secondPin.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(firstPin, miss.firstPin) && Objects.equals(secondPin, miss.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
