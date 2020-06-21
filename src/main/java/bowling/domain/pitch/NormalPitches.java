package bowling.domain.pitch;

import bowling.domain.exception.BowlingBuildingException;

import java.util.ArrayList;
import java.util.List;

public class NormalPitches {
    private static final int MAXIMUM_PITCHES_SUM = 10;
    private static final int FIRST_PITCH_INDEX = 0;

    private final List<Pitch> pitches = new ArrayList<>();

    public void recordPitch(int hitCounts) {
        pitches.add(new Pitch(hitCounts));
        validateNormalPitches();
    }

    private void validateNormalPitches() {
        int pitchesSum = pitches.stream()
                .mapToInt(Pitch::getHitCounts)
                .sum();
        if (pitchesSum > MAXIMUM_PITCHES_SUM) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_FRAME_RESULT);
        }
    }

    public boolean isStrike() {
        return pitches.get(FIRST_PITCH_INDEX).isStrike();
    }

    public int getPitchCounts() {
        return pitches.size();
    }
}
