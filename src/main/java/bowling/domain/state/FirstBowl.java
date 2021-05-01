package bowling.domain.state;

import bowling.domain.HitCount;

public final class FirstBowl extends Running {

    private static final int MAX_COUNT = 10;
    private final int firstCount;

    private FirstBowl(final int firstCount) {
        this.firstCount = firstCount;
    }

    public static final State from(final int firstCount) {
        return new FirstBowl(firstCount);
    }

    @Override
    public final State bowl(final HitCount hitCount) {
        if(firstCount+hitCount.count() == MAX_COUNT) {
            return Spare.of(firstCount, hitCount.count());
        }
        return Miss.from(firstCount, hitCount.count());
    }
}
