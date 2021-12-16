package bowling.domain;

import java.util.Objects;

public class Pitch {
    private final Pins pins;

    private Pitch() {
        this.pins = Pins.create();
    }

    private Pitch(int size) {
        this.pins = Pins.create(size);
    }

    public static Pitch first() {
        return new Pitch();
    }

    public Pitch next() {
        if (pins.isEmpty()) {
            return new Pitch();
        }
        return new Pitch(pins.size());
    }

    public Pins run(Pins fallDownPins) {
        return pins.fallDown(fallDownPins);
    }

    public Pins pins() {
        return pins;
    }

    public int pinsSize() {
        return pins.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch = (Pitch) o;
        return Objects.equals(pins, pitch.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
