package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PitchesGroup {

    private final List<Pitch> pitches = new ArrayList<>();

    public void recordPitch(int hitCounts) {
        pitches.add(new Pitch(hitCounts));
    }

    public List<Pitch> getPitches() {
        return Collections.unmodifiableList(pitches);
    }
}
