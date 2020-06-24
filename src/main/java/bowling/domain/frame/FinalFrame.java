package bowling.domain.frame;

import bowling.domain.pitch.FinalPitches;
import bowling.domain.pitch.Pitches;
import bowling.domain.score.FrameScore;
import bowling.domain.score.Score;

import java.util.List;

public class FinalFrame implements Frame {
    private static final int MAXIMUM_FINAL_FRAME_INDEX = 11;

    private final FinalPitches finalPitches = new FinalPitches();
    private final int index;

    private FinalFrame(int index) {
        this.index = index;
    }

    public static FinalFrame last(int index) {
        return new FinalFrame(index);
    }

    @Override
    public Frame next() {
        return new FinalFrame(index + NEXT_INDEX);
    }

    @Override
    public void bowl(Score score) {
        finalPitches.throwBall(score);
    }

    @Override
    public FrameScore calculateFrameScore(Frame lastFrame) {
        if (index == MAXIMUM_FINAL_FRAME_INDEX) {
            return FrameScore.of(lastFrame.getPitchScoreSum());
        }
        return lastFrame.isStrike() || lastFrame.isSpare() ?
                calculateFrameScoreWhenStrikeOrSpare(lastFrame) : FrameScore.of(lastFrame.getPitchScoreSum());
    }

    private FrameScore calculateFrameScoreWhenStrikeOrSpare(Frame lastFrame) {
        int frameScore = lastFrame.getPitchScoreSum() + finalPitches.getPitchScoreSum();
        if (lastFrame.isStrike() && finalPitches.isHavingSameCounts(PITCH_COUNT_FOR_CALCULATING_STRIKE)) {
            return FrameScore.of(frameScore);
        }
        return lastFrame.isSpare() && finalPitches.isHavingSameCounts(PITCH_COUNT_FOR_CALCULATING_SPARE) ?
                FrameScore.of(frameScore) : null;
    }

    @Override
    public boolean isMovableToNextFrame() {
        if (finalPitches.isHavingSameCounts(Pitches.MAXIMUM_NORMAL_PITCH_COUNTS)
                && finalPitches.isNotContainingStrikeOrSpare()) {
            return true;
        }
        return finalPitches.isHavingSameCounts(Pitches.MAXIMUM_FINAL_PITCH_COUNTS);
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public List<String> getScoreSignatures() {
        return finalPitches.getScoreSignatures();
    }

    @Override
    public int getPitchScoreSum() {
        return finalPitches.getPitchScoreSum();
    }

    @Override
    public int getIndex() {
        return index;
    }
}
