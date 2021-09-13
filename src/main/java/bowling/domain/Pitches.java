package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pitches {

    private static final int MAXIMUM_NORMAL_FRAME_PITCH = 2;
    private static final int FIRST_PITCH_INDEX = 0;
    private static final int FIRST_PITCH_SIZE = 1;

    private List<Pitch> pitches = new ArrayList<>();

    public boolean isEmpty() {
        return pitches.isEmpty();
    }

    public void add(final Pitch pitch) {
        pitches.add(pitch);
    }

    public boolean equalsToSize(final int size) {
        return pitches.size() == size;
    }

    public Pitch get(final int index) {
        return pitches.get(index);
    }

    public List<Pitch> value() {
        return pitches;
    }

    public int size() {
        return pitches.size();
    }

    boolean firstStrike() {
        return pitches.size() == FIRST_PITCH_SIZE && pitches.get(FIRST_PITCH_INDEX).isStrike();
    }

    public boolean isMaxSize() {
        return pitches.size() == MAXIMUM_NORMAL_FRAME_PITCH;
    }

    public List<Status> statuses() {
        return pitches.stream()
                .map(Pitch::status)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitches pitches1 = (Pitches) o;
        return Objects.equals(pitches, pitches1.pitches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitches);
    }
}
