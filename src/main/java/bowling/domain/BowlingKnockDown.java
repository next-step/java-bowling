package bowling.domain;

import bowling.exception.BowlingMaxCountException;

import java.util.Objects;

public class BowlingKnockDown {
    private static final int MAX_COUNT = 10;
    private final int count;

    public BowlingKnockDown(int count) {
        validate(count);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    private void validate(int count){
        if (count > MAX_COUNT) {
            throw new BowlingMaxCountException(MAX_COUNT);
        }
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
