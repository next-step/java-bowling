package bowling.domian.frame;

import bowling.domian.state.State;
import bowling.domian.state.running.FirstBowl;

public class FrameResult {

    public static FrameResult get(State strike, int i) {
        return new FrameResult();
    }

    public static FrameResult get(State firstBowl) {
        return new FrameResult();
    }

    public boolean isCalculateDone() {
        return false;
    }

    public boolean canCalculateScore() {
        return false;
    }

    public void calculateAdditional(FrameResult lastFrameResult) {
    }

    public boolean getTotalScore() {
        return false;
    }
}
