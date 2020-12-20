package bowling.domain;

import bowling.domain.interfaces.State;

import java.util.Objects;

public enum Result implements State {
    MISS,
    STRIKE,
    SPARE;

    @Override
    public State bowl(Pins pins, int count) {
        return this;
    }

    @Override
    public Pins getPins() {
        return pins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(pins, result.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
