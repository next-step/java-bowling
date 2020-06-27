package bowling.domain.frame;

import bowling.domain.pitch.FinalPitches;
import bowling.domain.pitch.Pitches;
import bowling.domain.score.FrameScore;
import bowling.domain.score.Score;

import java.util.List;

public class FinalFrame implements Frame {
    private static final int MAXIMUM_FINAL_FRAME_INDEX = 11;

    private final Pitches pitches;

    private FinalFrame(Pitches pitches) {
        this.pitches = pitches;
    }

    public static FinalFrame ofFinal() {
        return new FinalFrame(new FinalPitches());
    }

    @Override
    public Frame next(int index) {
        return null;
    }

    @Override
    public void bowl(Score score) {
        pitches.throwBall(score);
    }

    @Override
    public boolean isFinished() {
        if (pitches.isHavingSameCounts(Pitches.MAXIMUM_NORMAL_PITCH_COUNTS) &&
                !pitches.isSpare() && !pitches.isStrike()) {
            return true;
        }
        return pitches.isHavingSameCounts(Pitches.MAXIMUM_FINAL_PITCH_COUNTS);
    }

    @Override
    public List<String> getScoreSignatures() {
        return pitches.getScoreSignatures();
    }

    @Override
    public FrameScore calculateFrameScore() {
        return isFinished() ? FrameScore.ofMiss(pitches.getPitchScoreSum()) : null;
    }

    @Override
    public FrameScore delegateCalculation(FrameScore frameScore) {
        int pitchCounts = pitches.getCounts();
        for (int i = FIRST_INDEX; i <= pitchCounts; i++) {
            frameScore = accumulateFrameScore(i, frameScore);
        }
        return frameScore.isAbleToCalculate() ? frameScore : null;
    }

    private FrameScore accumulateFrameScore(int index, FrameScore frameScore) {
        int currentPitchScore = pitches.getCurrentScoreByIndex(index - 1);
        return frameScore.isAbleToCalculate() ? frameScore : frameScore.next(currentPitchScore);
    }
}
