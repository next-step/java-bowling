package bowling.domain;

import java.util.Objects;

public class BowlingKnockDown {
    private final int count;

    public BowlingKnockDown(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingKnockDown that = (BowlingKnockDown) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
