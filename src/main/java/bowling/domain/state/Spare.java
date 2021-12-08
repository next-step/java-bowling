package bowling.domain.state;

import bowling.domain.Pin;

import java.util.Objects;

public class Spare extends Finished {

    private final Pin firstPin;
    private final Pin secondPin;

    public Spare(Pin firstPin, Pin secondPin) {
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
        Spare miss = (Spare) o;
        return Objects.equals(firstPin, miss.firstPin) && Objects.equals(secondPin, miss.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
