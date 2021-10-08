package bowling.model.state;

import java.util.Objects;

import static bowling.controller.Main.stateResult;

import bowling.model.Pin;
import bowling.model.State;

public class SecondBowl implements State {
    private final Pin countOfPin;

    public SecondBowl(int countOfPin) {
        this.countOfPin = new Pin(countOfPin);
    }

    @Override
    public State bowl(int countOfPin) {
        Pin currentPin = this.countOfPin.add(countOfPin);

        if (currentPin.isStrike()) {
            return new ThirdSpare();
        }

        return new Miss(this.countOfPin, new Pin(countOfPin));
    }

    @Override
    public boolean isFinish(int frameNo) {
        return false;
    }

    @Override
    public String toString() {
        return stateResult.removeLast() + "|" + countOfPin.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecondBowl that = (SecondBowl) o;
        return Objects.equals(countOfPin, that.countOfPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPin);
    }
}
