package bowling.domain.frame;

import bowling.domain.pitch.Pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Pitches {
    private static final int ONE = 1;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    private List<Pitch> pitches;

    public Pitches() {
        this.pitches = new ArrayList<>();
    }

    public boolean add(int pinCount) {
        if (pitches.isEmpty()) {
            return pitches.add(Pitch.valueOf(pinCount));
        }

        return getLast().filter(count -> pitches.add(count.next(pinCount)))
                .isPresent();
    }

    public int size() {
        return pitches.size();
    }

    public boolean isEmpty() {
        return pitches.isEmpty();
    }

    public boolean isFull(int maxSize) {
        return pitches.size() == maxSize;
    }

    public int getPinCountTotal() {
        return pitches.stream()
                .reduce(0, (p1, p2) -> p2.add(p1), Integer::sum);
    }

    public Optional<Pitch> getFirst() {
        if (pitches.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(pitches.get(FIRST_INDEX));
    }

    public Optional<Pitch> getSecond() {
        if (pitches.size() <= SECOND_INDEX) {
            return Optional.empty();
        }
        return Optional.of(pitches.get(SECOND_INDEX));
    }

    public Optional<Pitch> getLast() {
        if (pitches.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(pitches.get(getLastIndex()));
    }

    private int getLastIndex() {
        return pitches.size() - ONE;
    }

    public boolean isLastPitchStrike() {
        Optional<Pitch> pitch = getLast();
        return pitch.map(Pitch::isStrike).orElse(false);
    }

    public boolean isLastPitchSpare() {
        Optional<Pitch> pitch = getLast();
        return pitch.map(Pitch::isSpare).orElse(false);
    }

    public List<Pitch> getPitches() {
        return pitches.stream()
                .map(Pitch::new)
                .collect(Collectors.toList());
    }
}
