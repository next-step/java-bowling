package bowling.frame;

import bowling.pitch.NormalPitches;
import bowling.score.Score;

public class NormalFrame implements Frame {
    private static final int FIRST_INDEX = 1;
    private static final int NEXT_INDEX = 1;
    private static final int MAXIMUM_NORMAL_FRAME_INDEX = 9;

    private final NormalPitches normalPitches = new NormalPitches();
    private final int index;

    private NormalFrame(int index) {
        this.index = index;
    }

    public static NormalFrame initiate() {
        return new NormalFrame(FIRST_INDEX);
    }

    @Override
    public Frame next() {
        return index == MAXIMUM_NORMAL_FRAME_INDEX ?
                FinalFrame.last(index + NEXT_INDEX) : new NormalFrame(index + NEXT_INDEX);
    }

    @Override
    public void bowl(Score score) {
        normalPitches.throwBall(score);
    }

    @Override
    public boolean isMovableToNextFrame() {
        return normalPitches.isFinished(MAXIMUM_NORMAL_PITCH_COUNTS) || normalPitches.isStrike();
    }

    @Override
    public int getIndex() {
        return index;
    }
}
