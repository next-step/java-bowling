package bowling.model;

import java.util.Objects;

import bowling.CannotBowlException;

public class Miss implements State {
    private final Point countOfPin;

    public Miss(Point countOfPin) {
        this.countOfPin = countOfPin;
    }

    @Override
    public State bowl(int countOfPin) throws CannotBowlException {
        throw new CannotBowlException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(countOfPin, miss.countOfPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPin);
    }
}
