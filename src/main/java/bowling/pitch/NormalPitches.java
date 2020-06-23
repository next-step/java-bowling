package bowling.pitch;

import bowling.exception.BowlingBuildingException;
import bowling.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalPitches implements Pitches {
    private static final int FIRST_INDEX = 0;

    private final List<Pitch> pitches = new ArrayList<>();

    @Override
    public void throwBall(Score score) {
        validateNormalPitches();
        Pitch pitch = createPitch(score);
        pitches.add(pitch);
    }

    private void validateNormalPitches() {
        if (pitches.size() == MAXIMUM_NORMAL_PITCH_COUNTS) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_NORMAL_PITCHES_TRY);
        }
    }

    private Pitch createPitch(Score score) {
        return pitches.isEmpty() ? Pitch.initiate(score) : pitches.get(FIRST_INDEX).next(score);
    }

    @Override
    public List<Pitch> getPitches() {
        return Collections.unmodifiableList(pitches);
    }

    public boolean isStrike() {
        return pitches.get(FIRST_INDEX).isStrike();
    }
}
