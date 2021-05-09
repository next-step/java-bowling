package bowling.domain.rollresult;

import bowling.domain.HitNumber;
import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RollResults {
    private final List<RollResultType> results;

    private RollResults(RollResultType result) {
        results = new ArrayList<>();
        results.add(result);
    }

    public RollResults(List<RollResultType> results) {
        this.results = results;
    }

    public static RollResults of(RollResultType result) {
        return new RollResults(result);
    }

    public static RollResults of(List<RollResultType> results) {
        return new RollResults(results);
    }

    public RollResults next(Pin pin, HitNumber number) {
        if (!hasNext() && isCleared()) {
            results.add(pin.firstHit(number));
            return of(results);
        }
        return of(pin.nextHit(getLast(), number));
    }

    public boolean isCleared() {
        return getLast().isStrike() || getLast().isSpare();
    }

    public boolean hasNext() {
        return getLast().hasNext();
    }

    public Score eval() {
        Score score = Score.of();
        for (RollResultType result : results) {
            score = score.calculate(result.eval());
        }
        return score;
    }

    private RollResultType getLast() {
        return results.get(results.size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RollResults that = (RollResults) o;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }

    @Override
    public String toString() {
        return "" + results + "";
    }

}
