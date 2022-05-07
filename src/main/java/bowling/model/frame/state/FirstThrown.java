package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.BallState;
import bowling.utility.Assert;

import java.util.Objects;

public final class FirstThrown implements BallState {

    private final Pins countOfHit;

    public FirstThrown(Pins countOfHit) {
        Assert.notNull(countOfHit, "countOfHit must not be null");
        validate(countOfHit);
        this.countOfHit = countOfHit;
    }

    public static FirstThrown from(Pins countOfHit) {
        return new FirstThrown(countOfHit);
    }

    @Override
    public BallState state(Pins countOfHit) {
        if (Pins.MAX.equals(this.countOfHit.sum(countOfHit))) {
            return Spare.of(this.countOfHit, countOfHit);
        }
        return SecondThrown.of(this.countOfHit, countOfHit);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    private void validate(Pins countOfHit) {
        if (countOfHit.equals(Pins.MAX)) {
            throw new IllegalArgumentException(String.format("countOfHit(%s) must be less than max pins(%s)", countOfHit, Pins.MAX));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfHit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FirstThrown that = (FirstThrown) o;
        return Objects.equals(countOfHit, that.countOfHit);
    }

    @Override
    public String toString() {
        return "FirstThrown{" +
                "countOfHit=" + countOfHit +
                '}';
    }
}
