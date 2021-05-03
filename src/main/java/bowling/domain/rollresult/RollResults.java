package bowling.domain.rollresult;

import bowling.domain.HitNumber;
import bowling.domain.Pin;

import java.util.Objects;

public class RollResults {
    private final RollResultType type;
    private final int total;

    private RollResults(RollResultType type, int total) {
        this.type = type;
        this.total = total;
    }

    public static RollResults of(RollResultType type) {
        return new RollResults(type, type.eval());
    }

    public static RollResults of(RollResultType type, int total) {
        return new RollResults(type, total);
    }

    public RollResults next(Pin pin, HitNumber number) {
        return of(pin.nextHit(type, number));
    }

    public boolean isCleared() {
        return type.isStrike() || type.isSpare();
    }

    public boolean hasNext() {
        return type.hasNext();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RollResults that = (RollResults) o;
        return total == that.total && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, total);
    }

    @Override
    public String toString() {
        return "" + type + "";
    }
}
