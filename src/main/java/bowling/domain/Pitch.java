package bowling.domain;

import bowling.strategy.PitchNumberStrategy;

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
        return new Pitch(pins.size());
    }

    public int run(PitchNumberStrategy numberStrategy) {
        int fallDownCount = numberStrategy.generate(pins.size());
        return pins.fallDown(fallDownCount);
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
