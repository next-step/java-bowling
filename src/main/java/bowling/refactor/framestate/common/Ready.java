package bowling.refactor.framestate.common;

import bowling.refactor.FrameScore;
import bowling.refactor.framestate.State;

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
}
