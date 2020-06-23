package bowling.pitch;

import bowling.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalPitches {

    private final List<Pitch> pitches = new ArrayList<>();

    public void throwBall(Score score) {
        if (pitches.size() == 0) {
            pitches.add(Pitch.initiate(score));
            return;
        }
        Pitch pitch = pitches.get(0);
        pitches.add(pitch.next(score));
    }

    public List<Pitch> getPitches() {
        return Collections.unmodifiableList(pitches);
    }
}
