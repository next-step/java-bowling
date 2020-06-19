package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PitchesGroup {

    private final List<Pitch> pitchesGroup = new ArrayList<>();

    public void recordPitch(int hitCounts) {
        pitchesGroup.add(new Pitch(hitCounts));
    }

    public List<Pitch> getPitchesGroup() {
        return Collections.unmodifiableList(pitchesGroup);
    }
}
