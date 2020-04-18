package bowling.framestate.common;

import bowling.FrameScore;
import bowling.framestate.State;

import java.util.Collections;

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

    @Override
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Collections.singletonList(10));
    }
}
