package bowling.domain.frame;

import java.util.List;
import java.util.Optional;

public class NormalFrame implements Frame {
    private static final int MAX_PIN_COUNT_SIZE = 2;
    private static final int MAX_PIN_COUNT = 10;

    private Pitches pitches;
    private Frame next;

    public NormalFrame() {
        pitches = new Pitches();
    }

    @Override public boolean addPinCount(int pinCount) {
        if (!isAddable(pinCount)) {
            return false;
        }

        if (isDone()) {
            return false;
        }

        return pitches.add(pinCount);
    }

    private boolean isAddable(int pinCount) {
        if (isPinCountsFull()) {
            return false;
        }

        Optional<Pitch> firstPinCount = pitches.getFirst();
        return firstPinCount.map(count -> !count.isOverMaxAfterAdd(pinCount))
                .orElse(true);
    }

    public FinalFrame createFinal() {
        next = new FinalFrame();
        return (FinalFrame) next;
    }

    private boolean isPinCountsFull() {
        return pitches.size() == MAX_PIN_COUNT_SIZE;
    }

    @Override public int getScore() {
        return pitches.getPinCountTotal();
    }

    @Override public boolean isDone() {
        if (isPinCountsFull()) {
            return true;
        }

        return pitches.getPinCountTotal() == MAX_PIN_COUNT;
    }

    @Override public List<Pitch> getPitches() {
        return pitches.getPitches();
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
