package bowling.model;

import java.util.Objects;

public class Ready implements State {
    Point countOfPin;
    
    public Ready() {
        this.countOfPin = new Point(0);
    }

    public Ready(Point countOfPin) {
        this.countOfPin = countOfPin;
    }

    @Override
    public State bowl(int countOfPin) {
        Point currentPin = new Point(countOfPin);

        if (currentPin.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(countOfPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ready ready = (Ready) o;
        return Objects.equals(countOfPin, ready.countOfPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPin);
    }
}
