package bowling.framestate;

import bowling.FrameScore;
import bowling.Pin;

import java.util.Collections;
import java.util.List;

import static bowling.Pin.MAX_PIN_COUNT;

public class Ready implements State {

    public Ready() {
    }

    public static Ready newInstance() {
        return new Ready();
    }

    @Override
    public State bowl(final Pin pinCount) {
        if (pinCount.isEqualTo(MAX_PIN_COUNT)) {
            return Strike.newInstance();
        }

        return FirstBowl.newInstance(pinCount);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createReady();
    }

    @Override
    public FrameScore sumBeforeScore(final FrameScore beforeScore) {
        return beforeScore.addNextAddingUpScores(Collections.emptyList());
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public List<Pin> getPins() {
        return Collections.emptyList();
    }
}
