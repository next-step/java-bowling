package bowling.domain.frame;

import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.pitch.FinalPitches;
import bowling.domain.pitch.Pitches;
import bowling.domain.score.FrameScore;
import bowling.domain.score.PitchScore;

import java.util.List;
import java.util.Optional;

public class FinalFrame implements Frame {

    private final Pitches pitches;

    private FinalFrame(Pitches pitches) {
        this.pitches = pitches;
    }

    public static Frame ofFinal() {
        return new FinalFrame(new FinalPitches());
    }

    @Override
    public Frame next(int index) {
        throw new BowlingBuildingException(BowlingBuildingException.FINAL_FRAME_LIMIT);
    }

    @Override
    public void bowl(PitchScore pitchScore) {
        pitches.throwBall(pitchScore);
    }

    @Override
    public boolean isFinished() {
        if (pitches.hasSamePitchCounts(Pitches.MAXIMUM_NORMAL_PITCH_COUNTS)
                && !pitches.hasSpare() && !pitches.hasStrike()) {
            return true;
        }
        return pitches.hasSamePitchCounts(Pitches.MAXIMUM_FINAL_PITCH_COUNTS);
    }

    @Override
    public Optional<FrameScore> calculateFrameScore() {
        if (!isFinished()) {
            return Optional.empty();
        }
        FrameScore frameScore = FrameScore.ofMiss(pitches.getPitchScoreSum());
        return Optional.of(frameScore);
    }

    @Override
    public Optional<FrameScore> delegateCalculation(FrameScore frameScore) {
        int pitchCounts = pitches.getPitchCounts();
        for (int i = ZERO_INDEX; i < pitchCounts; i++) {
            frameScore = accumulateFrameScore(i, frameScore);
        }
        return frameScore.isAbleToCalculate() ? Optional.of(frameScore) : Optional.empty();
    }

    private FrameScore accumulateFrameScore(int index, FrameScore frameScore) {
        int pitchScore = pitches.getPitchScoreByIndex(index);
        return frameScore.isAbleToCalculate() ? frameScore : frameScore.next(pitchScore);
    }

    @Override
    public List<String> getPitchScoreSignatures() {
        return pitches.getPitchScoreSignatures();
    }
}
