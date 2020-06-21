package bowling.domain.pitch;

import bowling.domain.bowling.BowlingPinsGroup;

import java.util.ArrayList;
import java.util.List;

public class NormalPitches {
    private static final int FIRST_PITCH_INDEX = 0;

    private final List<Pitch> pitches = new ArrayList<>();

    public void throwBall(int hitCounts, BowlingPinsGroup bowlingPinsGroup) {
        Pitch pitch = new Pitch(hitCounts);
        pitch.throwBall(bowlingPinsGroup);
        pitches.add(pitch);
    }

    public boolean isStrike() {
        return pitches.get(FIRST_PITCH_INDEX).isStrike();
    }

    public int getPitchCounts() {
        return pitches.size();
    }
}
