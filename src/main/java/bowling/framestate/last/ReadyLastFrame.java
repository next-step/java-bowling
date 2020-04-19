package bowling.framestate.last;

import bowling.FrameScore;
import bowling.Pin;
import bowling.framestate.State;

import java.util.Collections;
import java.util.List;

public class ReadyLastFrame implements State {

    public ReadyLastFrame() {
    }

    public static ReadyLastFrame newInstance() {
        return new ReadyLastFrame();
    }

    @Override
    public State bowl(final Pin pinCount) {
        if (pinCount.isEqualTo(Pin.MAX_PIN_COUNT)) {
            return StrikeLastFrame.newInstance();
        }

        return FirstBowlLastFrame.newInstance(pinCount);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createReady();
    }

    @Override
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Collections.emptyList());
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
