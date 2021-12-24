package bowling.domain;

import java.util.Objects;

public class BowlingPins {

    private final int count;

    public BowlingPins(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BowlingPins that = (BowlingPins) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return "BowlingPins{" +
                "countOfPins=" + count +
                '}';
    }

}
