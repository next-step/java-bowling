package bowling.frame;

import bowling.pitch.NormalPitches;
import bowling.score.Score;

public class NormalFrame implements Frame {

    private final NormalPitches normalPitches = new NormalPitches();
    private final int index;

    private NormalFrame(int index) {
        this.index = index;
    }

    public static NormalFrame initiate() {
        return new NormalFrame(0);
    }

    public void bowl(Score score) {
        normalPitches.throwBall(score);
    }

    public boolean isMovableToNextFrame() {
        if (normalPitches.getPitches().size() == 2 || normalPitches.isStrike()) {
            return true;
        }
        return false;
    }
}
