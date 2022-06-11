package bowling.domain.state;

import bowling.domain.Hit;
import bowling.domain.Score;

import java.util.Objects;

public class FirstHit extends Ongoing {

    private final Hit firstHit;

    public FirstHit(int firstHit) {
        this(Hit.valueOf(firstHit));
    }

    public FirstHit(Hit firstHit) {
        this.firstHit = firstHit;
    }

    @Override
    public State bowl(int hit) {
        Hit secondHit = Hit.valueOf(hit);
        if (firstHit.sum(secondHit).isMaximum()) {
            return new Spare(firstHit, secondHit);
        }
        return new Miss(firstHit, secondHit);
    }

    @Override
    public String description() {
        return firstHit.description();
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return score.addAdditionalScore(firstHit);
    }

    @Override
    public int bowlingCount() {
        return ONE_HIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstHit firstHit = (FirstHit) o;
        return Objects.equals(this.firstHit, firstHit.firstHit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstHit);
    }
}
