package bowling.domain;

import java.util.List;
import java.util.Objects;

public class Pitches {
    private final List<Pitch> pitches;

    public Pitches(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public int count() {
        return pitches.size();
    }

    public int sum() {
        return sum(pitches.size());
    }

    public int sum(int limit) {
        return pitches.stream()
                .limit(limit)
                .reduce(new Pitch(0), Pitch::sum)
                .intValue();
    }

    public void add(int point) {
        pitches.add(new Pitch(point));
    }

    public Pitch get(int index) {
        return pitches.get(index);
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
