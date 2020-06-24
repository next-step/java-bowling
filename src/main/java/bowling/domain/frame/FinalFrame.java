package bowling.domain.frame;

import bowling.domain.pitch.FinalPitches;
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
        return null;
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
}
