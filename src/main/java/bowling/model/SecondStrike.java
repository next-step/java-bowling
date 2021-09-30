package bowling.model;

import java.util.Objects;

public class SecondStrike implements State {
    private final Point point;

    public SecondStrike() {
        this.point = new Point(10);
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
        SecondStrike strike = (SecondStrike) o;
        return Objects.equals(point, strike.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

}
