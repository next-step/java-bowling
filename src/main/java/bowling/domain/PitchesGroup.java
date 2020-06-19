package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PitchesGroup {
    private static final int FIRST_PITCH_INDEX = 0;
    private static final int STRIKE_PIN_COUNTS = 10;
    private static final int MAXIMUM_PITCHES_COUNT = 2;

    private final List<Pitch> pitches = new ArrayList<>();

    public void recordPitch(int hitCounts) {
        pitches.add(new Pitch(hitCounts));
    }

    public boolean isMovableToNextFrame() {
        return isStrike() || isFinishingPitches();
    }

    private boolean isStrike() {
        return pitches.get(FIRST_PITCH_INDEX).getHitCounts() == STRIKE_PIN_COUNTS;
    }

    private boolean isFinishingPitches() {
        return pitches.size() == MAXIMUM_PITCHES_COUNT;
    }

    public List<Pitch> getPitches() {
        return Collections.unmodifiableList(pitches);
    }
}
