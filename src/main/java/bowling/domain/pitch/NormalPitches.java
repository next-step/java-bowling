package bowling.domain.pitch;

import bowling.domain.exception.NormalPitchTryException;
import bowling.domain.score.PitchScore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NormalPitches implements Pitches {
    private static final int FIRST_INDEX = 0;

    private final List<Pitch> pitches = new ArrayList<>();

    @Override
    public void throwBall(PitchScore pitchScore) {
        validateNormalPitches();
        Pitch pitch = createPitch(pitchScore);
        pitches.add(pitch);
    }

    private void validateNormalPitches() {
        if (pitches.size() == MAXIMUM_NORMAL_PITCH_COUNTS) {
            throw new NormalPitchTryException();
        }
    }

    private Pitch createPitch(PitchScore pitchScore) {
        return pitches.isEmpty() ? Pitch.initiate(pitchScore) : pitches.get(FIRST_INDEX).next(pitchScore);
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
    public List<String> getPitchScoreSignatures() {
        return pitches.stream()
                .map(Pitch::getPitchScoreSignature)
                .collect(Collectors.toList());
    }

    @Override
    public int getPitchScoreSum() {
        return pitches.stream()
                .mapToInt(Pitch::getPitchScore)
                .sum();
    }

    @Override
    public int getPitchScoreByIndex(int index) {
        return pitches.get(index).getPitchScore();
    }

    @Override
    public int getPitchCounts() {
        return pitches.size();
    }
}
