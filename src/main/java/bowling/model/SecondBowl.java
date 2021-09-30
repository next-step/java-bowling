package bowling.model;

import java.util.Objects;

public class SecondBowl implements State {
    private final Point countOfPin;

    public SecondBowl(int countOfPin) {
        this.countOfPin = new Point(countOfPin);
    }

    @Override
    public State bowl(int countOfPin) {
        Point currentPin = this.countOfPin.add(countOfPin);

        if (currentPin.isStrike()) {
            return new ThirdSpare();
        }

        return new Miss(currentPin);
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
