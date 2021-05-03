package bowling.domain.rollresult;

import bowling.domain.HitNumber;
import bowling.domain.Pin;

import java.util.Objects;

public class RollResult {
    private final RollResultType type;
    private final int total;

    private RollResult(RollResultType type, int total) {
        this.type = type;
        this.total = total;
    }

    public static RollResult of(RollResultType type) {
        return new RollResult(type, type.eval());
    }

    public static RollResult of(RollResultType type, int total) {
        return new RollResult(type, total);
    }

    public RollResult next(Pin pin, HitNumber number) {
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
        RollResult that = (RollResult) o;
        return total == that.total && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, total);
    }

}
