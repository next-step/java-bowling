package bowling.domain.state;

import bowling.domain.Hit;
import bowling.domain.Score;
import bowling.exception.NotCreateStateException;

import java.util.Objects;

public class Miss extends Finished {

    private static final String DESCRIPTION_FORMAT = "%s|%s";

    private final Hit firstHit;
    private final Hit secondHit;

    public Miss(Hit firstHit, Hit secondHit) {
        if (!firstHit.sum(secondHit).isLessThanMaximum()) {
            throw new NotCreateStateException(firstHit.sum(secondHit).toInt());
        }
        this.firstHit = firstHit;
        this.secondHit = secondHit;
    }

    @Override
    public boolean hasBonusChance() {
        return false;
    }

    @Override
    public int bowlingCount() {
        return TWO_HIT;
    }

    @Override
    public String description() {
        return String.format(DESCRIPTION_FORMAT, firstHit.description(), secondHit.description());
    }

    @Override
    public Score score() {
        return Score.miss(firstHit, secondHit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(firstHit, miss.firstHit) && Objects.equals(secondHit, miss.secondHit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstHit, secondHit);
    }
}
