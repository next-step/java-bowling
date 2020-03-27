package bowling.domain.state;

import java.util.Objects;

public class NextReady implements State{
    private int pins;

    public NextReady(int pins) {
        this.pins = pins;
    }

    @Override
    public State bowl(int pins) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NextReady nextReady = (NextReady) o;
        return pins == nextReady.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
