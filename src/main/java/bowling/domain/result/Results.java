package bowling.domain.result;

import bowling.domain.result.status.PinResultState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Results {

    private final List<PinResultState> results;

    public Results() {
        this(new ArrayList<>());
    }

    public Results(List<PinResultState> results) {
        this.results = results;
    }

    public void add(PinResultState resultState) {
        this.results.add(resultState);
    }

    public void clear() {
        this.results.clear();
    }

    public boolean isEmpty() {
        return results.isEmpty();
    }

    public int size() {
        return results.size();
    }

    public int sumAll() {
        return this.sum(result -> true);
    }

    public int sum(Predicate<? super PinResultState> predicate) {
        return results.stream()
            .filter(predicate)
            .mapToInt(PinResultState::getHitPinCount)
            .sum();
    }

    public boolean sizeLessThan(int number) {
        return size() < number;
    }

    public boolean sizeLessOrEqualThan(int number) {
        return size() <= number;
    }


    public List<PinResultState> getResults() {
        return Collections.unmodifiableList(results);
    }
}
