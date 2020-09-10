package bowling.pin.domain;

import java.util.Objects;

public class Pin {

    public static final int firstPin = 10;
    private int remainingPins;

    public Pin(int point) {
        this.remainingPins = firstPin - point;
    }

    public static Pin eachPitch(int point) {
        return new Pin(point);
    }

    public int getRemainingPins() {
        return remainingPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return remainingPins == pin.remainingPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remainingPins);
    }
}
