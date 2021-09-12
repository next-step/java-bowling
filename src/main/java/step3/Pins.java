package step3;

import java.util.Objects;
import step3.exceptions.PinNumberExecption;

public class Pins {
    private static final int MAX = 10;
    private static final int MIN = 0;

    private int remainPins;

    public Pins() {
        this(MAX);
    }

    public Pins(int numOfPin) {
        this.remainPins = numOfPin;
    }

    public void bowl(int falledPins) {
        this.remainPins -= falledPins;
        validPin();
    }

    private void validPin() {
        if (remainPins < MIN || remainPins > MAX) {
            throw new PinNumberExecption();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pins pins = (Pins) o;
        return remainPins == pins.remainPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remainPins);
    }
}
