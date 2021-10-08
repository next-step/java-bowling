package bowling.model.state;

import java.util.Objects;

import bowling.model.Pin;
import bowling.model.State;

public class FirstBowl implements State {
    private final Pin countOfPin;

    public FirstBowl(int countOfPin) {
        this.countOfPin = new Pin(countOfPin);
    }

    @Override
    public State bowl(int countOfPin) {
        Pin currentPin = this.countOfPin.add(countOfPin);
        if (currentPin.isStrike()) {
            return new Spare();
        }

        return new Miss(this.countOfPin, new Pin(countOfPin));
    }

    @Override
    public boolean isFinish(int frameNo) {
        return false;
    }

    @Override
    public String toString() {
        return countOfPin.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstBowl firstBowl = (FirstBowl) o;
        return Objects.equals(countOfPin, firstBowl.countOfPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPin);
    }
}
