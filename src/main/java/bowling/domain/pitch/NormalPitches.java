package bowling.domain.pitch;

import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.frame.Frame;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NormalPitches implements Pitches {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    private final List<Pitch> pitches = new ArrayList<>();

    @Override
    public void throwBall(Score score) {
        validateNormalPitches();
        Pitch pitch = createPitch(score);
        pitches.add(pitch);
    }

    private void validateNormalPitches() {
        if (isFinished(Frame.MAXIMUM_NORMAL_PITCH_COUNTS)) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_NORMAL_PITCHES_TRY);
        }
    }

    private Pitch createPitch(Score score) {
        return pitches.isEmpty() ? Pitch.initiate(score) : pitches.get(FIRST_INDEX).next(score);
    }

    public boolean isStrike() {
        return pitches.get(FIRST_INDEX).isStrike();
    }

    @Override
    public boolean isFinished(int pitchCounts) {
        return pitches.size() == pitchCounts;
    }

    @Override
    public List<String> getScoreSignatures() {
        return pitches.stream()
                .map(Pitch::getScoreSignature)
                .collect(Collectors.toList());
    }

    @Override
    public int getScoresSum() {
        return pitches.stream()
                .mapToInt(Pitch::getScore)
                .sum();
    }

    @Override
    public boolean isSpare() {
        return isFinished(Frame.MAXIMUM_NORMAL_PITCH_COUNTS) && pitches.get(SECOND_INDEX).isSpare();
    }
}
