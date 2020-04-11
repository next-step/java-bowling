package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public int getPintCountTotal() {
        return pinCountList.stream()
                .reduce(new PinCount(0), (p1, p2) -> new PinCount(p1.add(p2)))
                .add(new PinCount(0));
    }

    public Optional<PinCount> getFirst() {
        if(pinCountList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(pinCountList.get(0));
    }
}
