package bowling.domain.frame;

import bowling.domain.pitch.NormalPitches;
import bowling.domain.pitch.Pitches;
import bowling.domain.score.FrameScore;
import bowling.domain.score.Score;

import java.util.List;

public class NormalFrame implements Frame {
    private static final int MAXIMUM_NORMAL_FRAME_INDEX = 9;

    private final Pitches pitches;
    private Frame nextFrame;

    private NormalFrame(Pitches pitches) {
        this.pitches = pitches;
    }

    public static Frame initiate() {
        return new NormalFrame(new NormalPitches());
    }

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
        return pitches.isHavingSameCounts(Pitches.MAXIMUM_NORMAL_PITCH_COUNTS) || pitches.isStrike();
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
        return nextFrame != null ? nextFrame.delegateCalculation(frameScore) : null;
    }

    private FrameScore createFrameScore() {
        if (pitches.isStrike()) {
            return FrameScore.ofStrike();
        }
        return pitches.isSpare() ? FrameScore.ofSpare() : FrameScore.ofMiss(pitches.getPitchScoreSum());
    }

    @Override
    public FrameScore delegateCalculation(FrameScore frameScore) {
        int pitchCounts = pitches.getCounts();
        for (int i = FIRST_INDEX; i <= pitchCounts; i++) {
            frameScore = accumulateFrameScore(i, frameScore);
        }
        if (frameScore.isAbleToCalculate()) {
            return frameScore;
        }
        return isFinished() && nextFrame != null ? nextFrame.delegateCalculation(frameScore) : null;
    }

    private FrameScore accumulateFrameScore(int index, FrameScore frameScore) {
        int currentPitchScore = pitches.getCurrentScoreByIndex(index - 1);
        return frameScore.isAbleToCalculate() ? frameScore : frameScore.next(currentPitchScore);
    }

    @Override
    public List<String> getScoreSignatures() {
        return pitches.getScoreSignatures();
    }
}
