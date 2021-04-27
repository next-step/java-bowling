package bowling.domain;

import java.util.Objects;

public class Pitch {
    private final int fallenPins;

    public Pitch(int fallenPins) {
        this.fallenPins = fallenPins;
    }

    public static Pitch sum(Pitch previous, Pitch current) {
        return new Pitch(previous.intValue() + current.intValue());
    }

    public int intValue() {
        return fallenPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch = (Pitch) o;
        return fallenPins == pitch.fallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins);
    }
}
