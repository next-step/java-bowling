package bowling.domain.state.end;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Results {

    private final List<EndState> results;

    public Results() {
        this(new ArrayList<>());
    }

    public Results(List<EndState> results) {
        this.results = results;
    }

    public void add(EndState endState) {
        this.results.add(endState);
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

    public List<EndState> getResults() {
        return Collections.unmodifiableList(results);
    }
}
