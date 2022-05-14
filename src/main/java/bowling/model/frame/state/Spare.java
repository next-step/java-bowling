package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.BallState;
import bowling.utility.Assert;

import java.util.Objects;

public final class Spare implements BallState {

    private static final int REMAIN_COUNT = 1;

    private final Pins firstHit;

    private Spare(Pins firstHit) {
        Assert.notNull(firstHit, "firstHit must not be null");
        this.firstHit = firstHit;
    }

    public static Spare from(Pins firstHit) {
        return new Spare(firstHit);
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
    public int remainCount() {
        return REMAIN_COUNT;
    }

    public Pins firstHit() {
        return firstHit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstHit);
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
        return Objects.equals(firstHit, spare.firstHit);
    }

    @Override
    public String toString() {
        return "Spare{" +
                "firstHit=" + firstHit +
                '}';
    }
}
