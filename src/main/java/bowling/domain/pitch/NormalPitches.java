package bowling.domain.pitch;

import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public boolean hasSamePitchCounts(int pitchCounts) {
        return pitches.size() == pitchCounts;
    }

    @Override
    public boolean hasStrike() {
        return pitches.stream()
                .anyMatch(Pitch::isStrike);
    }

    @Override
    public boolean hasSpare() {
        return pitches.stream()
                .anyMatch(Pitch::isSpare);
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

    @Override
    public int getPitchScoreByIndex(int index) {
        return pitches.get(index).getScore();
    }

    @Override
    public int getPitchCounts() {
        return pitches.size();
    }
}
