package bowling.frame;

import bowling.pitch.NormalPitches;
import bowling.score.Score;

public class NormalFrame implements Frame {
    private static final int FIRST_INDEX = 1;
    private static final int NEXT_INDEX = 1;

    private final NormalPitches normalPitches = new NormalPitches();
    private final int index;

    private NormalFrame(int index) {
        this.index = index;
    }

    public static NormalFrame initiate() {
        return new NormalFrame(FIRST_INDEX);
    }

    public NormalFrame next() {
        return new NormalFrame(index + NEXT_INDEX);
    }

    public void bowl(Score score) {
        normalPitches.throwBall(score);
    }

    public boolean isMovableToNextFrame() {
        return normalPitches.isFinished() || normalPitches.isStrike();
    }

    public int getIndex() {
        return index;
    }
}
