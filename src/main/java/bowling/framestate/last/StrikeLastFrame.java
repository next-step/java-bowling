package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import bowling.framestate.State;

import java.util.Collections;
import java.util.List;

public class StrikeLastFrame implements State {

    public StrikeLastFrame() {
    }

    public static StrikeLastFrame newInstance() {
        return new StrikeLastFrame();
    }

    @Override
    public State bowl(final Pin pinCount) {
        return StrikeLastFrameOver.newInstance(pinCount);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(1));
    }

    @Override
    public FrameScore addNextAddingUpFrameScore(FrameScore beforeScore) {
        return beforeScore.addNextAddingUpScores(Collections.singletonList(Score.ofAllPins()));
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public List<Pin> getPins() {
        return Collections.singletonList(Pin.ofMax());
    }
}
