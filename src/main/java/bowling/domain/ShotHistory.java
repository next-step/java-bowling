package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShotHistory {

    private final List<Shot> shots;

    public ShotHistory(List<Shot> shots) {
        this.shots = shots;
    }

    public boolean add(Shot shot) {
        return shots.add(shot);
    }

    public boolean add(ShotHistory shotHistory) {
        return shots.addAll(shotHistory.getShots());
    }

    public boolean contains(Shot shot) {
        return shots.contains(shot);
    }

    public Stream<Shot> stream() {
        return shots.stream();
    }

    public String generateSymbolString(String delimiter) {
        return shots
                .stream()
                .map(Shot::getSymbol)
                .collect(Collectors.joining(delimiter));
    }

    public List<Shot> getShots() {
        return shots;
    }
}
