package bowling.domain;

import java.util.Objects;

public class BowlingPins {

    private final int countOfPins;

    public BowlingPins(int countOfPins) {
        this.countOfPins = countOfPins;
    }

    public BowlingPins down(int countOfHits) {
        return new BowlingPins(this.countOfPins - countOfHits);
    }

    public int getCountOfPins() {
        return countOfPins;
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
        return countOfPins == that.countOfPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPins);
    }

    @Override
    public String toString() {
        return "BowlingPins{" +
                "countOfPins=" + countOfPins +
                '}';
    }

}
