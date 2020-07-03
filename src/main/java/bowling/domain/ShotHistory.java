package bowling.domain;

import java.util.ArrayList;
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

    public ShotHistory add(ShotHistory shotHistory) {
        List<Shot> shots = new ArrayList<>();
        shots.addAll(this.shots);
        shots.addAll(shotHistory.getShots());
        return new ShotHistory(shots);
    }

    public boolean contains(Shot shot) {
        return shots.contains(shot);
    }

    public Shot getLast() {
        return shots.get(shots.size() - 1);
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
