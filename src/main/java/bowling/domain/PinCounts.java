package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PinCounts {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    private List<PinCount> pinCounts;

    public PinCounts() {
        this.pinCounts = new ArrayList<>();
    }

    public boolean add(int pinCount) {
        if (pinCounts.isEmpty()) {
            return pinCounts.add(PinCount.valueOf(pinCount));
        }

        return getLast().filter(count -> pinCounts.add(count.next(pinCount)))
                .isPresent();
    }

    public int size() {
        return pinCounts.size();
    }

    public boolean isEmpty() {
        return pinCounts.isEmpty();
    }

    public int getPinCountTotal() {
        return pinCounts.stream()
                .reduce(0, (p1, p2) -> p2.add(p1), Integer::sum);
    }

    public Optional<PinCount> getFirst() {
        if (pinCounts.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(pinCounts.get(FIRST_INDEX));
    }

    public Optional<PinCount> getSecond() {
        if (pinCounts.size() <= SECOND_INDEX) {
            return Optional.empty();
        }
        return Optional.of(pinCounts.get(SECOND_INDEX));
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
