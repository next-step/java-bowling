package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PinCounts {

    private final List<PinCount> pinCounts = new ArrayList<>();

    public void add(int pinCount) {
        pinCounts.add(new PinCount(pinCount));
    }

    public List<PinCount> counts() {
        return Collections.unmodifiableList(new ArrayList<>(pinCounts));
    }

    public int totalCount() {
        return pinCounts.stream()
                .map(pinCount -> pinCount.count())
                .reduce(0, Integer::sum);
    }

    public int size() {
        return pinCounts.size();
    }
}
