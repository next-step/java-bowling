package bowling.refactor.framestate.common;

import bowling.refactor.FrameScore;
import bowling.refactor.framestate.State;

public class Strike implements State {

    public Strike() {
    }

    public static Strike newInstance() {
        return new Strike();
    }

    @Override
    public State Bowl(final int countOfPin) {
        throw new IllegalStateException("No more bowl.");
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createStrike();
    }
}
