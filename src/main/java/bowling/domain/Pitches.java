package bowling.domain;

import java.util.Collections;
import java.util.List;

public class Pitches {
    private final List<Pitch> pitches;

    public Pitches(final List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public List<Pitch> getPitches() {
        return Collections.unmodifiableList(pitches);
    }

    public List<Pitch> getPitches(Pitch lastPitch) {
        return pitches.subList(0, pitches.indexOf(lastPitch) + 1);
    }

    public Pitches add(List<Pitch> pitches) {
        this.pitches.addAll(pitches);
        return this;
    }
}
