package bowling.refactor.framestate.last;

import bowling.refactor.FrameScore;
import bowling.refactor.LeftScoreCount;
import bowling.refactor.Score;
import bowling.refactor.framestate.State;

public class StrikeLastFrame implements State {

    public StrikeLastFrame() {
    }

    public static StrikeLastFrame newInstance() {
        return new StrikeLastFrame();
    }

    @Override
    public State Bowl(final int countOfPin) {
        return StrikeLastFrameOver.newInstance(countOfPin);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.newInstance(Score.of(10), LeftScoreCount.of(1));
    }
}
