package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PinCounts {
    private List<PinCount> pinCounts;
    private int maxSize;

    public PinCounts(int maxSize) {
        this.maxSize = maxSize;
        this.pinCounts = new ArrayList<>();
    }

    public boolean add(int pinCount) {
        if (isFull()) {
            return false;
        }
        if (pinCounts.isEmpty()) {
            return pinCounts.add(PinCount.valueOf(pinCount));
        }

        getLast().ifPresent(p -> pinCounts.add(p.next(pinCount)));
        return true;
    }

    public boolean isFull() {
        return pinCounts.size() == maxSize;
    }

    public int size() {
        return pinCounts.size();
    }

    public int getPinCountTotal() {
        return pinCounts.stream()
                .reduce(0, (p1, p2) -> p2.add(p1), Integer::sum);
    }

    public Optional<PinCount> getFirst() {
        if (pinCounts.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(pinCounts.get(0));
    }

    private Optional<PinCount> getLast() {
        if (pinCounts.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(pinCounts.get(getLastIndex()));
    }

    private int getLastIndex() {
        return pinCounts.size() - 1;
    }

    public List<PinCount> getPinCounts() {
        return pinCounts.stream()
                .map(PinCount::new)
                .collect(Collectors.toList());
    }
}
