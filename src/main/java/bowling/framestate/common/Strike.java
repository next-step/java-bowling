package bowling.framestate.common;

import bowling.FrameScore;
import bowling.framestate.State;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Strike implements State {

    public Strike() {
    }

    public static Strike newInstance() {
        return new Strike();
    }

    @Override
    public State bowl(final int countOfPin) {
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

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public List<Integer> getPins() {
        return Collections.singletonList(10);
    }
}
