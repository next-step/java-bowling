package bowling.domain.frame;

import java.util.List;
import java.util.Optional;

public class FinalFrame implements Frame {
    private static final int MAX_PIN_COUNT_SIZE = 3;
    private static final int MIN_PIN_COUNT_FOR_THIRD = 10;

    private Pitches pitches;

    public FinalFrame() {
        pitches = new Pitches();
    }

    @Override public boolean addPinCount(int pinCount) {
        if (!isAddable(pinCount)) {
            return false;
        }

        return pitches.add(pinCount);
    }

    private boolean isAddable(int pinCount) {
        if (isDone()) {
            return false;
        }

        if (hasThirdChance()) {
            return true;
        }

        Optional<Pitch> lastPinCount = pitches.getLast();
        return lastPinCount.map(pc -> !pc.isOverMaxAfterAdd(pinCount))
                .orElse(true);
    }

    private boolean hasThirdChance() {
        Pitch firstPitch = pitches.getFirst()
                .orElse(Pitch.empty());
        Pitch secondPitch = pitches.getSecond()
                .orElse(Pitch.empty());

        if (firstPitch.isStrike()) {
            return true;
        } else return secondPitch.isSpare();
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
        return pitches.size() == 2 &&
                pitches.getPinCountTotal() < MIN_PIN_COUNT_FOR_THIRD;
    }

    @Override public List<Pitch> getPitches() {
        return pitches.getPitches();
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
