package bowling.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ShotResults implements Iterable<ShotResult> {
    private final List<ShotResult> shotResults = new ArrayList<>();

    public void add(ShotResult shotResult) {
        shotResults.add(shotResult);
    }

    public int sumOfPinDown() {
        return shotResults.stream().mapToInt(ShotResult::getNumOfPinDown).sum();
    }

    public int size() {
        return shotResults.size();
    }

    @Override
    public Iterator<ShotResult> iterator() {
        return shotResults.iterator();
    }

    public Stream<ShotResult> stream() {
        return shotResults.stream();
    }
}
