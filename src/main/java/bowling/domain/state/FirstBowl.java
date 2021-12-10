package bowling.domain.state;

import bowling.domain.frame.Pin;

import java.util.Objects;

public class FirstBowl extends Running {

    private final Pin firstPin;

    public FirstBowl(Pin firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public State bowl(Pin pin) {
        if (firstPin.add(pin).isMaxCount()) {
            return new Spare(firstPin, pin);
        }

        return new Miss(firstPin, pin);
    }

    @Override
    public String viewString() {
        return firstPin.viewString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FirstBowl firstBowl = (FirstBowl) o;
        return Objects.equals(firstPin, firstBowl.firstPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin);
    }
}
