package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DownPins {
    private final static int FIRST_PITCHING = 1;
    private final static int PREVIOUS_INDEX_MINUS = 2;

    private final List<DownPin> downPins = new ArrayList<>();

    public DownPins() {}

    public void add(int value) {
        downPins.add(new DownPin(value));
    }

    public boolean isFirst() {
        return downPins.size() == FIRST_PITCHING;
    }

    public int size() {
        return downPins.size();
    }

    public int sum() {
        return downPins.stream()
                .mapToInt(DownPin::count)
                .sum();
    }

    public List<DownPin> downPins() {
        return Collections.unmodifiableList(downPins);
    }

    public int count(int index) {
        return downPins.get(index)
                .count();
    }

    public int previousCount() {
        if (downPins().size() == FIRST_PITCHING) {
            return sum();
        }
        return downPins.get(downPins.size() - PREVIOUS_INDEX_MINUS)
                .count();
    }

    @Override
    public String toString() {
        return "DownPins{" +
                "downPins=" + downPins +
                '}';
    }
}
