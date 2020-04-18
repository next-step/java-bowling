package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
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
    public State bowl(final int countOfPin) {
        return StrikeLastFrameOver.newInstance(countOfPin);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.newInstance(Score.of(10), LeftScoreCount.of(1));
    }

    @Override
    public FrameScore addingUpFrameScore(FrameScore beforeScore) {
        return beforeScore.addingUp(Collections.singletonList(10));
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public List<Integer> getPins() {
        return Collections.singletonList(10);
    }
}
