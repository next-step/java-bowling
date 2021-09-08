package bowling.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShotResults implements Iterable<ShotResult> {
    private final List<ShotResult> shotResults = new ArrayList<>();

    public void add(ShotResult shotResult) {
        shotResults.add(shotResult);
    }

    public boolean sumIsMax() {
        return ShotResult.fromSumOf(shotResults) == ShotResult.MAX;
    }

    public boolean firstIsMax() {
        return shotResults.get(0) == ShotResult.MAX;
    }

    public int size() {
        return shotResults.size();
    }

    public boolean empty() {
        return shotResults.size() == 0;
    }

    @Override
    public Iterator<ShotResult> iterator() {
        return shotResults.iterator();
    }

    @Override
    public String toString() {
        return shotResults.stream().map(ShotResult::toString).reduce((accu, curr) -> accu + "|" + curr).orElse("");
    }
}
