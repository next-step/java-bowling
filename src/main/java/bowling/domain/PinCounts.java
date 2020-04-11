package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PinCounts {
    private List<PinCount> pinCountList;
    private int maxSize;

    public PinCounts(int maxSize) {
        this.maxSize = maxSize;
        this.pinCountList = new ArrayList<>();
    }

    public boolean add(PinCount pinCount) {
        if (isFull()) {
            return false;
        }

        pinCountList.add(pinCount);
        return true;
    }

    public boolean isFull() {
        return pinCountList.size() == maxSize;
    }

    public int size() {
        return pinCountList.size();
    }

    public int getPinCountTotal() {
        return pinCountList.stream()
                .reduce(0, (p1, p2) -> p2.add(p1), Integer::sum);
    }

    public Optional<PinCount> getFirst() {
        if (pinCountList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(pinCountList.get(0));
    }

    public List<Integer> getPinCountList() {
        return pinCountList.stream()
                .map(PinCount::getPinCount)
                .collect(Collectors.toList());
    }
}
