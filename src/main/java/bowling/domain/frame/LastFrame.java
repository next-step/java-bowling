package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.CommonState;
import bowling.domain.state.LastProgress;

public class LastFrame extends Frame {

    private LastFrame(CommonState state) {
        super(state);
    }

    public static LastFrame of() {
        return new LastFrame(LastProgress.of());
    }

    @Override
    public boolean isBowlingFinish() {
        return state.isFinish();
    }

    @Override
    public Score getScore() {
        return state.score();
    }

    @Override
    protected Score addBonusScore(Score score) {
        return state.addScore(score);
    }

}
