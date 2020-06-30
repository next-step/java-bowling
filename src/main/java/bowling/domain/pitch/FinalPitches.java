package bowling.domain.pitch;

import bowling.domain.exception.FinalPitchTryException;
import bowling.domain.score.PitchScore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalPitches implements Pitches {
    private static final int INDEX_CONSTANT = 1;

    private final List<Pitch> pitches = new ArrayList<>();

    @Override
    public void throwBall(PitchScore pitchScore) {
        if (pitches.size() >= MAXIMUM_NORMAL_PITCH_COUNTS) {
            validateFinalPitches();
        }
        Pitch pitch = createPitch(pitchScore);
        pitches.add(pitch);
    }

    private void validateFinalPitches() {
        if (hasNotStrikeOrSpare() || pitches.size() == MAXIMUM_FINAL_PITCH_COUNTS) {
            throw new FinalPitchTryException();
        }
    }

    private boolean hasNotStrikeOrSpare() {
        return pitches.stream()
                .noneMatch(pitch -> pitch.isStrike() || pitch.isSpare());
    }

    private Pitch createPitch(PitchScore pitchScore) {
        if (pitches.isEmpty()) {
            return Pitch.initiate(pitchScore);
        }
        Pitch lastPitch = pitches.get(pitches.size() - INDEX_CONSTANT);
        return lastPitch.isSpare() || lastPitch.isStrike() ? Pitch.initiate(pitchScore) : lastPitch.next(pitchScore);
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
