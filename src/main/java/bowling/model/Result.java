package bowling.model;

import java.util.Objects;

public class Result {
    private State before;
    private State current;

    public Result(State before, State current) {
        this.before = before;
        this.current = current;
    }

    public State getBefore() {
        return before;
    }

    public boolean isStrike() {
        return current instanceof Strike;
    }

    public boolean isSpare() {
        return current instanceof Spare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(before, result.before) && Objects.equals(current, result.current);
    }

    @Override
    public int hashCode() {
        return Objects.hash(before, current);
    }
}
