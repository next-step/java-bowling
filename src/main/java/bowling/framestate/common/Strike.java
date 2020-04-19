package bowling.framestate.common;

import bowling.FrameScore;
import bowling.Pin;
import bowling.Score;
import bowling.framestate.State;

import java.util.Collections;
import java.util.List;

import static bowling.Pin.MAX_PIN_COUNT;

public class Strike implements State {

    public Strike() {
    }

    public static Strike newInstance() {
        return new Strike();
    }

    @Override
    public State bowl(final Pin countOfPin) {
        throw new IllegalStateException("No more bowl.");
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createStrike();
    }

    @Override
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Collections.singletonList(Score.ofAllPins()));
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public List<Pin> getPins() {
        return Collections.singletonList(Pin.of(MAX_PIN_COUNT));
    }
}
