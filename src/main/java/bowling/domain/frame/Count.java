package bowling.domain.frame;

import java.util.Objects;

public class Count {
    private final static int INCREMENT = 1;
    private static final int BONUS_COUNT = 3;

    private final int count;

    private Count(final int count) {
        this.count = count;
    }

    public static Count ofFirst() {
        return new Count(0);
    }

    Count increaseNormalFrameCount() {
        if (count < Frames.NORMAL_FRAME_COUNT) {
            return new Count(count + INCREMENT);
        }
        return this;
    }

    Count increaseFinalFrameCount() {
        if (count < BONUS_COUNT) {
            return new Count(count + INCREMENT);
        }
        return this;
    }

    boolean isBonusCount() {
        return count == BONUS_COUNT;
    }

    boolean isNotBonusCount() {
        return count == BONUS_COUNT - INCREMENT;
    }

    int getCount() {
        return count;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Count count1 = (Count) o;
        return count == count1.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
