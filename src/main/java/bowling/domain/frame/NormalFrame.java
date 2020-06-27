package bowling.domain.frame;

import bowling.domain.pitch.NormalPitches;
import bowling.domain.pitch.Pitches;
import bowling.domain.score.FrameScore;
import bowling.domain.score.Score;

import java.util.List;

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
        return this.nextFrame;
    }

    @Override
    public void bowl(Score score) {
        pitches.throwBall(score);
    }

    @Override
    public boolean isFinished() {
        return pitches.hasSamePitchCounts(Pitches.MAXIMUM_NORMAL_PITCH_COUNTS) || pitches.hasStrike();
    }

    @Override
    public FrameScore calculateFrameScore() {
        if (!isFinished()) {
            return null;
        }
        FrameScore frameScore = createFrameScore();
        if (frameScore.isAbleToCalculate()) {
            return frameScore;
        }
        return hasNextFrame() ? nextFrame.delegateCalculation(frameScore) : null;
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
    public FrameScore delegateCalculation(FrameScore frameScore) {
        int pitchCounts = pitches.getPitchCounts();
        for (int i = ZERO_INDEX; i < pitchCounts; i++) {
            frameScore = accumulateFrameScore(i, frameScore);
        }
        if (frameScore.isAbleToCalculate()) {
            return frameScore;
        }
        return isFinished() && hasNextFrame() ? nextFrame.delegateCalculation(frameScore) : null;
    }

    private FrameScore accumulateFrameScore(int index, FrameScore frameScore) {
        int currentPitchScore = pitches.getPitchScoreByIndex(index);
        return frameScore.isAbleToCalculate() ? frameScore : frameScore.next(currentPitchScore);
    }

    @Override
    public List<String> getScoreSignatures() {
        return pitches.getScoreSignatures();
    }
}
