package bowling.model;

import java.util.Objects;

public class Spare implements State {
    private final Point countOfPin;

    public Spare() {
        this.countOfPin = new Point(10);
    }

    public Spare(Point countOfPin) {
        this.countOfPin = countOfPin;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(countOfPin, spare.countOfPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPin);
    }
}
