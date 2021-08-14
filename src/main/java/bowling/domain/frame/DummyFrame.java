package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.CommonState;
import bowling.domain.state.Start;

public class DummyFrame extends Frame {

    private DummyFrame(CommonState state) {
        super(state);
    }

    public static DummyFrame of() {
        return new DummyFrame(Start.of());
    }

    @Override
    public Score getScore() {
        return Score.NULL;
    }

    @Override
    protected Score addBonusScore(Score score) {
        return score;
    }

}
