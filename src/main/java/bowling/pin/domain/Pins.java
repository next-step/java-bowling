package bowling.pin.domain;

import bowling.state.domain.State;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Pins {

    private List<Pin> pins;

    public Pins(List<Pin> pins) {
        this.pins = pins;
    }

    public static Pins eachPitchResult(List<Pin> pins, Pin pin) {
        if (pins.size() == 2) {
            pins.clear();
        }
        pins.add(pin);
        return new Pins(pins);
    }

    public int size() {
        return pins.size();
    }

    public List<Pin> getPins() {
        return Collections.unmodifiableList(pins);
    }

    public Pin getPinsIndex(int index) {
        return pins.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return Objects.equals(pins, pins1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }

    @Override
    public String toString() {
        return String.valueOf(pins);
    }
}
