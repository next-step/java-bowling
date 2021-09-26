package bowling.model;

import java.util.Objects;

public class Gutter implements State {
    private Point firstPin;
    private Point secondPin;

    public Gutter(Point firstPin, Point secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public State bowl(int countOfPin) {
        Point currentPin = new Point(countOfPin);
        if (currentPin.isStrike()) {
            return new Strike();
        }

        return new Ready(currentPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gutter gutter = (Gutter) o;
        return Objects.equals(firstPin, gutter.firstPin) && Objects.equals(secondPin, gutter.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
