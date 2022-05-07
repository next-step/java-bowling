package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.BallState;
import bowling.utility.Assert;

import java.util.Objects;

public final class SecondThrown implements BallState {

    private final Pins firstHit;
    private final Pins secondHit;

    private SecondThrown(Pins firstHit, Pins secondHit) {
        Assert.notNull(firstHit, "firstHit must not be null");
        Assert.notNull(secondHit, "secondHit must not be null");
        validateSum(firstHit, secondHit);
        this.firstHit = firstHit;
        this.secondHit = secondHit;
    }

    public static SecondThrown of(Pins firstHit, Pins secondHit) {
        return new SecondThrown(firstHit, secondHit);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public BallState state(Pins countOfHit) {
        throw new IllegalStateException(String.format("spare(%s) can not throw ball", this));
    }

    private void validateSum(Pins firstHit, Pins secondHit) {
        if (Pins.MAX.equals(firstHit.sum(secondHit))) {
            throw new IllegalArgumentException(String.format("first pins(%s) and second pins(%s) must be thrown", firstHit, secondHit));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstHit, secondHit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SecondThrown that = (SecondThrown) o;
        return Objects.equals(firstHit, that.firstHit) && Objects.equals(secondHit, that.secondHit);
    }

    @Override
    public String toString() {
        return "SecondThrown{" +
                "firstHit=" + firstHit +
                ", secondHit=" + secondHit +
                '}';
    }
}
