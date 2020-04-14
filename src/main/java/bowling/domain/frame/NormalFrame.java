package bowling.domain.frame;

import java.util.List;
import java.util.Optional;

public class NormalFrame implements Frame {
    private static final int MAX_PIN_COUNT_SIZE = 2;
    private static final int MAX_PIN_COUNT = 10;

    private PinCounts pinCounts;
    private Frame next;

    public NormalFrame() {
        pinCounts = new PinCounts();
    }

    @Override public boolean addPinCount(int pinCount) {
        if (!isAddable(pinCount)) {
            return false;
        }

        if (isDone()) {
            return false;
        }

        return pinCounts.add(pinCount);
    }

    private boolean isAddable(int pinCount) {
        if (isPinCountsFull()) {
            return false;
        }

        Optional<PinCount> firstPinCount = pinCounts.getFirst();
        return firstPinCount.map(count -> !count.isOverMaxAfterAdd(pinCount))
                .orElse(true);
    }

    public FinalFrame createFinal() {
        next = new FinalFrame();
        return (FinalFrame) next;
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

        return pinCounts.getPinCountTotal() == MAX_PIN_COUNT;
    }

    @Override public List<PinCount> getPinCounts() {
        return pinCounts.getPinCounts();
    }

    @Override public Frame getNext() {
        return next;
    }

    @Override public boolean isLast() {
        return false;
    }

    @Override public NormalFrame createNext() {
        next = new NormalFrame();
        return (NormalFrame) next;
    }
}
