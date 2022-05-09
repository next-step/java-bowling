package bowling.model.frame.finalization;

import bowling.model.Pins;
import bowling.model.frame.BallState;
import bowling.utility.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class BonusHit {

    private final BallState fromState;
    private final List<Pins> counts;

    private BonusHit(BallState fromState, List<Pins> counts) {
        Assert.notNull(fromState, "fromState must not be null");
        Assert.notEmpty(counts, "counts must not be null");
        this.fromState = fromState;
        this.counts = counts;
    }

    static BonusHit of(BallState fromState, Pins count) {
        Assert.notNull(count, "counts must not be null");
        return of(fromState, Collections.singletonList(count));
    }

    static BonusHit of(BallState fromState, List<Pins> counts) {
        return new BonusHit(fromState, counts);
    }

    BonusHit addedCount(Pins count) {
        List<Pins> addedCount = new ArrayList<>(this.counts);
        addedCount.add(count);
        return of(this.fromState, addedCount);
    }

    public BallState fromState() {
        return fromState;
    }

    public List<Pins> counts() {
        return Collections.unmodifiableList(this.counts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromState, counts);
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
        return Objects.equals(fromState, bonusHit.fromState) && Objects.equals(counts, bonusHit.counts);
    }

    @Override
    public String toString() {
        return "BonusHit{" +
                "fromState=" + fromState +
                ", counts=" + counts +
                '}';
    }
}
