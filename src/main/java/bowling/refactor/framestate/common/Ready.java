package bowling.refactor.framestate.common;

import bowling.refactor.FrameScore;
import bowling.refactor.framestate.State;

import java.util.Arrays;

public class Ready implements State {

    public Ready() {
    }

    public static Ready newInstance() {
        return new Ready();
    }

    @Override
    public State Bowl(final int countOfPin) {
        if (countOfPin == 10) {
            return Strike.newInstance();
        }

        return FirstBowl.newInstance(countOfPin);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createReady();
    }

    @Override
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Arrays.asList(0, 0));
    }
}
