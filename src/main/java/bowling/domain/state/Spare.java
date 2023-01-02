package bowling.domain.state;

import bowling.domain.Pin;

import java.util.Objects;

public class Spare extends Finished {

    public static final String SPARE_MESSAGE = "|/";

    private final Pin firstPin;

    public Spare(Pin firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public String toString() {
        return firstPin + SPARE_MESSAGE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(firstPin, spare.firstPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin);
    }
}
