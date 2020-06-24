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
    public int calculateFrameScore(Frame lastFrame) {
        if (index == 11) {
            return lastFrame.getScoresSum();
        }
        if (lastFrame.isSpare() || lastFrame.isStrike()) {
            return strikeOrSpare(lastFrame);
        }
        return lastFrame.getScoresSum();
    }

    @Override
    public FrameNumericScore calculateFrameScore2(Frame nextFrame) {
        return null;
    }

    private int strikeOrSpare(Frame lastFrame) {
        if (lastFrame.isStrike() && this.finalPitches.isFinished(2)) {
            return lastFrame.getScoresSum() + finalPitches.getScoresSum();
        }
        if (lastFrame.isSpare() && this.finalPitches.isFinished(1)) {
            return lastFrame.getScoresSum() + finalPitches.getScoresSum();
        }
        return 0;
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
