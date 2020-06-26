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
    public FrameScore calculateFrameScore(FrameScore lastFrameScore) {
        return null;
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
