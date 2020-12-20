package bowling.domain;

import bowling.exception.BowlingMaxCountException;

import java.util.Objects;

public class BowlingKnockDown {
    public static final int MAX_COUNT = 10;
    private final int count;

    private BowlingKnockDown(int count) {
        this.count = count;
    }

    public static BowlingKnockDown of(int count) {
        validate(count);
        return new BowlingKnockDown(count);
    }

    public int getCount() {
        return count;
    }

    private static void validate(int count){
        if (count > MAX_COUNT) {
            throw new BowlingMaxCountException();
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
