package bowling.domain;

import bowling.domain.interfaces.State;

import java.util.Objects;

public abstract class Result implements State {
    private Pins pins;

    public Result(Pins pins) {
        this.pins = pins;
    }

    @Override
    public State bowl(int count) {
        return null;
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
