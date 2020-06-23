package bowling.pitch;

import bowling.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalPitches {
    private static final int FIRST_INDEX = 0;

    private final List<Pitch> pitches = new ArrayList<>();

    public void throwBall(Score score) {
        Pitch pitch = createPitch(score);
        pitches.add(pitch);
    }

    private Pitch createPitch(Score score) {
        if (pitches.isEmpty()) {
            return Pitch.initiate(score);
        }
        return pitches.get(FIRST_INDEX).next(score);
    }

    public List<Pitch> getPitches() {
        return Collections.unmodifiableList(pitches);
    }
}
