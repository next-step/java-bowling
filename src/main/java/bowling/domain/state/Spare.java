package bowling.domain.state;

import bowling.domain.value.Pins;

import java.util.Objects;

public class Spare extends Finished{

    private final Pins firstPin;
    private final Pins secondPin;

    public Spare(Pins firstPin, Pins secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Spare spare = (Spare) o;
        return Objects.equals(firstPin, spare.firstPin) && Objects.equals(secondPin, spare.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
