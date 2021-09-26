package bowling.model;

import java.util.Objects;

public class FirstBowl implements State {
    private Point countOfPin;

    public FirstBowl(Point countOfPin) {
        this.countOfPin = countOfPin;
    }

    @Override
    public State bowl(int countOfPin) {
        Point currentPin = new Point(countOfPin);

        if (this.countOfPin.add(countOfPin).isStrike()) {
            return new Spare(this.countOfPin, currentPin);
        }

        if (currentPin.isGutter()) {
            return new Gutter(this.countOfPin, currentPin);
        }

        return new Miss(this.countOfPin, currentPin);
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
