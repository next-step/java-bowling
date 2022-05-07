package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.BallState;
import bowling.utility.Assert;

import java.util.Objects;

public final class Spare implements BallState {

    private static final int BONUS_COUNT = 1;

    private final Pins firstHit;
    private final Pins secondHit;

    private Spare(Pins firstHit, Pins secondHit) {
        Assert.notNull(firstHit, "firstHit must not be null");
        Assert.notNull(secondHit, "secondHit must not be null");
        validate(firstHit, secondHit);
        this.firstHit = firstHit;
        this.secondHit = secondHit;
    }

    public static Spare of(Pins firstHit, Pins secondHit) {
        return new Spare(firstHit, secondHit);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public BallState state(Pins countOfHit) {
        throw new IllegalStateException(String.format("spare(%s) can not throw ball", this));
    }

    @Override
    public int bonusCount() {
        return BONUS_COUNT;
    }

    private void validate(Pins firstHit, Pins secondHit) {
        if (Pins.MAX.equals(firstHit)) {
            throw new IllegalArgumentException(String.format("first pins(%s) must not be max pins", firstHit));
        }
        if (!Pins.MAX.equals(firstHit.sum(secondHit))) {
            throw new IllegalArgumentException(String.format("sum of first pins(%s) and second pins(%s) must be max pins", firstHit, secondHit));
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
        Spare spare = (Spare) o;
        return Objects.equals(firstHit, spare.firstHit) && Objects.equals(secondHit, spare.secondHit);
    }

    @Override
    public String toString() {
        return "Spare{" +
                "firstHit=" + firstHit +
                ", secondHit=" + secondHit +
                '}';
    }
}
