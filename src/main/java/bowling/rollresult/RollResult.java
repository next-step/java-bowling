package bowling.rollresult;

import bowling.HitNumber;
import bowling.Pin;

import java.util.Objects;

public class RollResult {
    private final RollResultType type;
    private final int total;

    public RollResult(RollResultType type, int total) {
        this.type = type;
        this.total = total;
    }

    public static RollResult of(RollResultType type) {
        return new RollResult(type, type.eval());
    }

    public RollResult next(Pin pin, HitNumber number) {
        return of(pin.secondHit(type, number));
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
