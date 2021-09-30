package bowling.model;

import bowling.CannotBowlException;

import java.util.Objects;

public class ThirdSpare implements State {
    private final Point countOfPin;

    public ThirdSpare() {
        this.countOfPin = new Point(10);
    }

    @Override
    public State bowl(int countOfPin) throws CannotBowlException {
        throw new CannotBowlException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThirdSpare spare = (ThirdSpare) o;
        return Objects.equals(countOfPin, spare.countOfPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPin);
    }
}
