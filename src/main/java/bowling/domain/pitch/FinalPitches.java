package bowling.domain.pitch;

import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        if (isNotContainingStrikeOrSpare() || isHavingSameCounts(MAXIMUM_FINAL_PITCH_COUNTS)) {
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
    public boolean isHavingSameCounts(int pitchCounts) {
        return pitches.size() == pitchCounts;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    public boolean isNotContainingStrikeOrSpare() {
        return pitches.stream()
                .noneMatch(pitch -> pitch.isStrike() || pitch.isSpare());
    }

    @Override
    public List<String> getScoreSignatures() {
        return pitches.stream()
                .map(Pitch::getScoreSignature)
                .collect(Collectors.toList());
    }

    @Override
    public int getPitchScoreSum() {
        return pitches.stream()
                .mapToInt(Pitch::getScore)
                .sum();
    }
}
