package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.LastInProgress;
import bowling.domain.state.State;

import java.util.List;

public class LastFrame extends Frame {

    protected LastFrame(State state) {
        super(state);
    }

    public static LastFrame init() {
        return new LastFrame(LastInProgress.init());
    }

    @Override
    protected void appendFrame(List<Frame> frames) {
        // do nothing
    }

    @Override
    public boolean isBowlingEnd() {
        return state.isEnd();
    }

    @Override
    public Score getScore() {
        return state.score();
    }

    @Override
    public Score addBonusScore(Score score) {
        return state.addScore(score);
    }
}
