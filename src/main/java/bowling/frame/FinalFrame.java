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
        if (finalPitches.isFinishingNormalPitches() && finalPitches.isNotContainingStrikeOrSpare()) {
            return true;
        }
        return finalPitches.isFinishingPitchesWithBonus();
    }

    @Override
    public int getIndex() {
        return index;
    }
}
