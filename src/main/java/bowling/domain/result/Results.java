package bowling.domain.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Results {

    private final List<ResultState> results;

    public Results() {
        this(new ArrayList<>());
    }

    public Results(List<ResultState> results) {
        this.results = results;
    }

    public void add(ResultState resultState) {
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

    public List<ResultState> getResults() {
        return Collections.unmodifiableList(results);
    }
}
