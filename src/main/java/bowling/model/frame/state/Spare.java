package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.BallState;
import bowling.utility.Assert;

import java.util.Objects;

public final class Spare implements BallState {

    private static final int REMAIN_COUNT = 1;
    private static final Pins FIRST_AND_SECOND_SUM_PINS = Pins.MAX;
    private static final String MARK_FORMAT = "%s|/";

    private final Pins firstHit;

    private Spare(Pins firstHit) {
        Assert.notNull(firstHit, "firstHit must not be null");
        Assert.isFalse(firstHit.equals(FIRST_AND_SECOND_SUM_PINS), String.format("firstHit(%s) must not equal sum pins(%s)", firstHit, FIRST_AND_SECOND_SUM_PINS));
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

    @Override
    public String mark() {
        return String.format(MARK_FORMAT, CountToMarkConverter.convert(firstHit.count()));
    }

    @Override
    public int sumPinsCount() {
        return FIRST_AND_SECOND_SUM_PINS.count();
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
