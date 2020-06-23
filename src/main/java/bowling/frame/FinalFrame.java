package bowling.frame;

import bowling.pitch.FinalPitches;
import bowling.score.Score;

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
    public int getIndex() {
        return index;
    }
}
