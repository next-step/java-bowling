package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.State;

import java.util.List;

public class DummyFrame extends Frame {
    protected DummyFrame(State state) {
        super(state);
    }

    @Override
    protected void appendFrame(List<Frame> frames) {
        // do nothing
    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    protected Score addBonusScore(Score score) {
        return score;
    }

}
