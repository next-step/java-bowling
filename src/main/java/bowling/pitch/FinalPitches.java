package bowling.pitch;

import bowling.exception.BowlingBuildingException;
import bowling.score.Score;

import java.util.ArrayList;
import java.util.List;

public class FinalPitches implements Pitches {

    private final List<Pitch> pitches = new ArrayList<>();

    @Override
    public void throwBall(Score score) {
        validateFinalPitches();
        Pitch pitch = createPitch(score);
        pitches.add(pitch);
    }

    private void validateFinalPitches() {
        if (pitches.size() < 2) {
            return;
        }
        boolean isContainingStrikeOrSpare = pitches.stream()
                .anyMatch(pitch -> pitch.isStrike() || pitch.isSpare());
        if (!isContainingStrikeOrSpare || pitches.size() == 3) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_FINAL_PITCH_TRY);
        }
    }

    private Pitch createPitch(Score score) {
        if (pitches.isEmpty()) {
            return Pitch.initiate(score);
        }
        Pitch pitch = getCurrentPitch();
        if (pitch.isSpare() || pitch.isStrike()) {
            return Pitch.initiate(score);
        }
        return pitch.next(score);
    }

    private Pitch getCurrentPitch() {
        return pitches.get(pitches.size() - 1);
    }

    @Override
    public List<Pitch> getPitches() {
        return pitches;
    }
}
