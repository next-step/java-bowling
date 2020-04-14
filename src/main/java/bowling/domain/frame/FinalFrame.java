package bowling.domain.frame;

import java.util.List;
import java.util.Optional;

public class FinalFrame implements Frame {
    private static final int MAX_PIN_COUNT_SIZE = 3;
    private static final int MIN_PIN_COUNT_FOR_THIRD = 10;

    private PinCounts pinCounts;

    public FinalFrame() {
        pinCounts = new PinCounts();
    }

    @Override public boolean addPinCount(int pinCount) {
        if (!isAddable(pinCount)) {
            return false;
        }

        return pinCounts.add(pinCount);
    }

    private boolean isAddable(int pinCount) {
        if (isDone()) {
            return false;
        }

        if (hasThirdChance()) {
            return true;
        }

        Optional<PinCount> lastPinCount = pinCounts.getLast();
        return lastPinCount.map(pc -> !pc.isOverMaxAfterAdd(pinCount))
                .orElse(true);
    }

    private boolean hasThirdChance() {
        PinCount firstPinCount = pinCounts.getFirst()
                .orElse(PinCount.empty());
        PinCount secondPinCount = pinCounts.getSecond()
                .orElse(PinCount.empty());

        if (firstPinCount.isStrike()) {
            return true;
        } else return secondPinCount.isSpare();
    }

    private boolean isPinCountsFull() {
        return pinCounts.size() == MAX_PIN_COUNT_SIZE;
    }

    @Override public int getScore() {
        return pinCounts.getPinCountTotal();
    }

    @Override public boolean isDone() {
        if (isPinCountsFull()) {
            return true;
        }
        return pinCounts.size() == 2 &&
                pinCounts.getPinCountTotal() < MIN_PIN_COUNT_FOR_THIRD;
    }

    @Override public List<PinCount> getPinCounts() {
        return pinCounts.getPinCounts();
    }

    @Override public Frame createNext() {
        return null;
    }

    @Override public Frame getNext() {
        return null;
    }

    @Override public boolean isLast() {
        return true;
    }
}
