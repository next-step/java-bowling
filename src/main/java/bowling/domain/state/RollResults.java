package bowling.domain.state;

import bowling.domain.HitNumber;
import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RollResults {
    private final List<State> results;

    private RollResults(State result) {
        results = new ArrayList<>();
        results.add(result);
    }

    public RollResults(List<State> results) {
        this.results = results;
    }

    public static RollResults of(State result) {
        return new RollResults(result);
    }

    public static RollResults of(List<State> results) {
        return new RollResults(results);
    }

    public RollResults next(Pin pin, HitNumber number) {
        if (!hasNext()) {
            List<State> next = new ArrayList<>(results);
            next.add(pin.nextHit(Ready.of(), number));
            return of(next);
        }
        State newState = pin.nextHit(getLast(), number);
        if (results.size() == 1) {
            return of(newState);
        }
        return of(Arrays.asList(results.get(0), newState));
    }

    public boolean isCleared() {
        return getLast().isStrike() || getLast().isSpare();
    }

    public boolean hasNext() {
        return getLast().hasNext();
    }

    public Score eval() {
        Score score = Score.of();
        for (State result : results) {
            score = score.calculate(result.eval());
        }
        return score;
    }

    private State getLast() {
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
