package bowling.framestate.common;

import bowling.FrameScore;
import bowling.framestate.State;

import java.util.Collections;
import java.util.List;

public class Ready implements State {

    public Ready() {
    }

    public static Ready newInstance() {
        return new Ready();
    }

    @Override
    public State bowl(final int countOfPin) {
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
        return beforeScore.addingUp(Collections.EMPTY_LIST);
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public List<Integer> getPins() {
        return Collections.emptyList();
    }
}
