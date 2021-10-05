package bowling.model.state;

import java.util.Objects;

import static bowling.controller.Main.stateResult;

import bowling.CannotBowlException;
import bowling.model.Point;
import bowling.model.State;

public class ThirdStrike implements State {
    private final Point countOfPin;

    public ThirdStrike() {
        this.countOfPin = new Point(10);
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
        return stateResult.removeLast() + "|X";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThirdStrike strike = (ThirdStrike) o;
        return Objects.equals(countOfPin, strike.countOfPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPin);
    }

}
