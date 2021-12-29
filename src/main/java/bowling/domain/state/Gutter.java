package bowling.domain.state;

import bowling.domain.value.Pins;

import java.util.Objects;

public class Gutter extends Finished {

    private final Pins firstPin;
    private final Pins secondPin;

    public Gutter(Pins firstPin, Pins secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public String getMark() {
        return checkGutter(firstPin) + DELIMITER + checkGutter(secondPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gutter gutter = (Gutter) o;
        return Objects.equals(firstPin, gutter.firstPin) && Objects.equals(secondPin, gutter.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
