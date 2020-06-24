package bowling.domain.frame;

import bowling.domain.pitch.FinalPitches;
import bowling.domain.score.FrameNumericScore;
import bowling.domain.score.Score;

import java.util.List;

public class FinalFrame implements Frame {

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
        return new FinalFrame(index + 1);
    }

    @Override
    public void bowl(Score score) {
        finalPitches.throwBall(score);
    }

    @Override
    public boolean isMovableToNextFrame() {
        if (finalPitches.isFinished(MAXIMUM_NORMAL_PITCH_COUNTS) && finalPitches.isNotContainingStrikeOrSpare()) {
            return true;
        }
        return finalPitches.isFinished(MAXIMUM_FINAL_PITCH_COUNTS);
    }

    @Override
    public List<String> getScoreSignatures() {
        return finalPitches.getScoreSignatures();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public FrameNumericScore calculateFrameScore(Frame lastFrame) {
        if (index == 11) {
            return FrameNumericScore.of(lastFrame.getScoresSum());
        }
        if (lastFrame.isSpare() || lastFrame.isStrike()) {
            return calculateFrameScoreWhenStrikeOrSpare(lastFrame);
        }
        return FrameNumericScore.of(lastFrame.getScoresSum());
    }

    private FrameNumericScore calculateFrameScoreWhenStrikeOrSpare(Frame lastFrame) {
        int frameScore = lastFrame.getScoresSum() + finalPitches.getScoresSum();
        if (lastFrame.isStrike() && finalPitches.isFinished(MAXIMUM_NORMAL_PITCH_COUNTS)) {
            return FrameNumericScore.of(frameScore);
        }
        return lastFrame.isSpare() && finalPitches.isFinished(1) ? FrameNumericScore.of(frameScore) : null;
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public int getScoresSum() {
        return finalPitches.getScoresSum();
    }

    @Override
    public boolean isSpare() {
        return false;
    }
}
