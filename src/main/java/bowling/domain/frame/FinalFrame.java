package bowling.domain.frame;

import bowling.domain.pitch.Pitch;

import java.util.List;
import java.util.Optional;

public class FinalFrame implements Frame {
    private static final int MAX_PIN_COUNT_SIZE = 3;
    private static final int MIN_PIN_COUNT_FOR_THIRD = 10;

    private Pitches pitches;

    public FinalFrame() {
        this.pitches = new Pitches();
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


    @Override public Optional<Integer> getScore() {
        if (!isDone()) {
            return Optional.empty();
        }

        return Optional.of(pitches.getPinCountTotal());
    }

    @Override public boolean isDone() {
        if (pitches.isEmpty()) {
            return false;
        }

        if (pitches.isFull(MAX_PIN_COUNT_SIZE)) {
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

    @Override public Frame createNext(Frame frame) {
        return null;
    }

    @Override public boolean isLast() {
        return true;
    }

    @Override public Optional<Integer> getScoreForTwoPitches() {
        Optional<Pitch> firstPitch = pitches.getFirst();
        Optional<Pitch> secondPitch = pitches.getSecond();
        if (!firstPitch.isPresent() || !secondPitch.isPresent()) {
            return Optional.empty();
        }

        int firstCount = firstPitch.get().getCount();
        int secondCount = secondPitch.get().getCount();
        return Optional.of(firstCount + secondCount);
    }

    @Override public Optional<Integer> getScoreForOnePitch() {
        return pitches.getFirst()
                .map(Pitch::getCount);
    }
}
