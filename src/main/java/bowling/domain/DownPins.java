package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DownPins {
    private final static int FIRST_PITCHING = 1;

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

    public DownPin get(int index) {
        return downPins.get(index);
    }

    @Override
    public String toString() {
        return "DownPins{" +
                "downPins=" + downPins +
                '}';
    }
}
