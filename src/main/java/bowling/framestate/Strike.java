package bowling.framestate;

import bowling.FrameScore;
import bowling.Pin;
import bowling.Score;

import java.util.Collections;
import java.util.List;

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
    public FrameScore addNextAddingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addNextAddingUpScores(Collections.singletonList(Score.ofAllPins()));
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public List<Pin> getPins() {
        return Collections.singletonList(Pin.ofMax());
    }
}
