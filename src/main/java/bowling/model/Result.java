package bowling.model;

import java.util.Objects;

public class Result {
    private BowlingResult before;
    private BowlingResult current;

    public Result(BowlingResult before, BowlingResult current) {
        this.before = before;
        this.current = current;
    }

    public BowlingResult getBefore() {
        return before;
    }

    public boolean isStrike() {
        if (current == BowlingResult.STRIKE) {
            return true;
        }

        return false;
    }

    public boolean isSpare() {
        if (current == BowlingResult.SPARE) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return before == result.before && current == result.current;
    }

    @Override
    public int hashCode() {
        return Objects.hash(before, current);
    }
}
