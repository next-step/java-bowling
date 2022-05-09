package bowling.model.frame.finalization;

import bowling.model.Pins;
import bowling.model.frame.BallState;
import bowling.utility.Assert;

import java.util.Objects;

public final class BonusThrown implements BallState {

    private static final int MINIMUM_COUNT = 0;
    private static final int MINUS_COUNT_STEP = 1;

    private final BonusHit bonusHit;
    private final int restCount;

    private BonusThrown(BonusHit bonusHit, int restCount) {
        Assert.notNull(bonusHit, "bonusHit must not be null");
        validateCount(restCount);
        this.bonusHit = bonusHit;
        this.restCount = restCount;
    }

    static BonusThrown of(BonusHit bonusHit, int restCount) {
        return new BonusThrown(bonusHit, restCount);
    }

    @Override
    public boolean isEnd() {
        return restCount <= MINIMUM_COUNT;
    }

    @Override
    public BallState state(Pins countOfHit) {
        validateState();
        return of(bonusHit.addedCount(countOfHit), restCount - MINUS_COUNT_STEP);
    }

    public BonusHit bonusHit() {
        return bonusHit;
    }

    private void validateState() {
        if (isLessThanMinimum(restCount - MINUS_COUNT_STEP)) {
            throw new IllegalStateException(String.format("rest count(%d) must be greater than or equal %d to create a state.", restCount, MINIMUM_COUNT));
        }
    }

    private void validateCount(int restCount) {
        if (isLessThanMinimum(restCount)) {
            throw new IllegalArgumentException(String.format("rest count(%d) must be greater than %d", restCount, MINIMUM_COUNT));
        }
    }

    private boolean isLessThanMinimum(int restCount) {
        return restCount < MINIMUM_COUNT;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusHit, restCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BonusThrown that = (BonusThrown) o;
        return restCount == that.restCount && Objects.equals(bonusHit, that.bonusHit);
    }

    @Override
    public String toString() {
        return "BonusThrown{" +
                "bonusHit=" + bonusHit +
                ", restCount=" + restCount +
                '}';
    }
}
