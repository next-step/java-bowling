package bowling.pitch;

import bowling.exception.BowlingBuildingException;
import bowling.score.Score;

import java.util.ArrayList;
import java.util.List;

public class FinalPitches implements Pitches {
    private static final int INDEX_CONSTANT = 1;

    private final List<Pitch> pitches = new ArrayList<>();

    @Override
    public void throwBall(Score score) {
        if (pitches.size() >= MAXIMUM_NORMAL_PITCH_COUNTS) {
            validateFinalPitches();
        }
        Pitch pitch = createPitch(score);
        pitches.add(pitch);
    }

    private void validateFinalPitches() {
        boolean isContainingStrikeOrSpare = pitches.stream()
                .anyMatch(pitch -> pitch.isStrike() || pitch.isSpare());
        if (!isContainingStrikeOrSpare || pitches.size() == MAXIMUM_FINAL_PITCH_COUNTS) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_FINAL_PITCH_TRY);
        }
    }

    private Pitch createPitch(Score score) {
        if (pitches.isEmpty()) {
            return Pitch.initiate(score);
        }
        Pitch lastPitch = pitches.get(pitches.size() - INDEX_CONSTANT);
        return lastPitch.isSpare() || lastPitch.isStrike() ? Pitch.initiate(score) : lastPitch.next(score);
    }

    @Override
    public List<Pitch> getPitches() {
        return pitches;
    }
}
