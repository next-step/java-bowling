package bowling.domain.state;

import bowling.domain.Hit;
import bowling.domain.Score;
import bowling.exception.NotCreateStateException;

import java.util.Objects;

public class Spare extends Finished {

    private static final String DESCRIPTION_FORMAT = "%s|%s";
    private static final String SYMBOL = "/";

    private final Hit firstHit;
    private final Hit secondHit;

    public Spare(Hit firstHit, Hit secondHit) {
        if (!firstHit.sum(secondHit).isMaximum()) {
            throw new NotCreateStateException(firstHit.sum(secondHit).toInt());
        }
        this.firstHit = firstHit;
        this.secondHit = secondHit;
    }

    @Override
    public boolean hasBonusChance() {
        return true;
    }

    @Override
    public int bowlingCount() {
        return TWO_HIT;
    }

    @Override
    public String description() {
        return String.format(DESCRIPTION_FORMAT, firstHit.description(), SYMBOL);
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(firstHit, spare.firstHit) && Objects.equals(secondHit, spare.secondHit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstHit, secondHit);
    }
}
