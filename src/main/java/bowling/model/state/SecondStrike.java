package bowling.model.state;

import java.util.Objects;

import static bowling.controller.Main.stateResult;

import bowling.model.Pin;
import bowling.model.State;

public class SecondStrike implements State {
    private final Pin countOfPin;

    public SecondStrike() {
        this.countOfPin = new Pin(10);
    }

    @Override
    public State bowl(int countOfPin) {
        Pin currentPin = new Pin(countOfPin);

        if (currentPin.isStrike()) {
            return new ThirdStrike();
        }

        return new Miss(this.countOfPin, new Pin(countOfPin));
    }

    @Override
    public boolean isFinish(int frameNo) {
        return false;
    }

    @Override
    public String toString() {
        return stateResult.removeLast() + "|X";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecondStrike strike = (SecondStrike) o;
        return Objects.equals(countOfPin, strike.countOfPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPin);
    }

}
