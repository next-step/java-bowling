package bowling.model.state;

import bowling.model.Point;
import bowling.model.State;

import java.util.Objects;

public class SecondStrike implements State {
    private final Point countOfPin;

    public SecondStrike() {
        this.countOfPin = new Point(10);
    }

    @Override
    public State bowl(int countOfPin) {
        Point currentPin = new Point(countOfPin);

        if (currentPin.isStrike()) {
            return new ThirdStrike();
        }

        return new Miss(currentPin);
    }

    @Override
    public boolean isFinish(int frameNo) {
        return false;
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
