package bowling.domain;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Bowlings implements Iterable<Bowling> {

    private final List<Bowling> values;

    public Bowlings(List<Name> names) {
        this.values = names.stream()
                .map(Bowling::new)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Bowling> iterator() {
        return values.iterator();
    }

    public boolean isGameNotEnd() {
        return values.stream()
                .anyMatch(bowling -> !bowling.isGameEnd());
    }
}
