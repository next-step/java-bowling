package bowling.model.frame.finalization;

import bowling.model.Pins;
import bowling.model.frame.BallState;
import bowling.utility.Assert;

import java.util.Objects;

final class BonusHit {

    private final BallState fromState;
    private final Pins count;

    private BonusHit(BallState fromState, Pins count) {
        Assert.notNull(fromState, "fromState must not be null");
        Assert.notNull(count, "count must not be null");
        this.fromState = fromState;
        this.count = count;
    }

    static BonusHit of(BallState fromState, Pins count) {
        return new BonusHit(fromState, count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromState, count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BonusHit bonusHit = (BonusHit) o;
        return Objects.equals(fromState, bonusHit.fromState) && Objects.equals(count, bonusHit.count);
    }

    @Override
    public String toString() {
        return "BonusHit{" +
                "fromState=" + fromState +
                ", count=" + count +
                '}';
    }
}
