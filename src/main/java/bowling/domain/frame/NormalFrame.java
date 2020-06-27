package bowling.domain.frame;

import bowling.domain.pitch.NormalPitches;
import bowling.domain.pitch.Pitches;
import bowling.domain.score.FrameScore;
import bowling.domain.score.PitchScore;

import java.util.List;
import java.util.Optional;

public class NormalFrame implements Frame {

    private final Pitches pitches;
    private Frame nextFrame;

    private NormalFrame(Pitches pitches) {
        this.pitches = pitches;
    }

    public static Frame initiate() {
        return new NormalFrame(new NormalPitches());
    }

    @Override
    public Frame next(int index) {
        this.nextFrame = (index != MAXIMUM_NORMAL_FRAME_INDEX) ? NormalFrame.initiate() : FinalFrame.ofFinal();
        return nextFrame;
    }

    @Override
    public void bowl(PitchScore pitchScore) {
        pitches.throwBall(pitchScore);
    }

    @Override
    public boolean isFinished() {
        return pitches.hasSamePitchCounts(Pitches.MAXIMUM_NORMAL_PITCH_COUNTS) || pitches.hasStrike();
    }

    @Override
    public Optional<FrameScore> calculateFrameScore() {
        if (!isFinished()) {
            return Optional.empty();
        }
        FrameScore frameScore = createFrameScore();
        if (frameScore.isAbleToCalculate()) {
            return Optional.of(frameScore);
        }
        return hasNextFrame() ? nextFrame.delegateCalculation(frameScore) : Optional.empty();
    }

    private FrameScore createFrameScore() {
        if (pitches.hasStrike()) {
            return FrameScore.ofStrike();
        }
        return pitches.hasSpare() ? FrameScore.ofSpare() : FrameScore.ofMiss(pitches.getPitchScoreSum());
    }

    private boolean hasNextFrame() {
        return nextFrame != null;
    }

    @Override
    public Optional<FrameScore> delegateCalculation(FrameScore frameScore) {
        int pitchCounts = pitches.getPitchCounts();
        for (int i = ZERO_INDEX; i < pitchCounts; i++) {
            frameScore = accumulateFrameScore(i, frameScore);
        }
        if (frameScore.isAbleToCalculate()) {
            return Optional.of(frameScore);
        }
        return isFinished() && hasNextFrame() ? nextFrame.delegateCalculation(frameScore) : Optional.empty();
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
