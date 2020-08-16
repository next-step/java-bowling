package bowling.domian.state.running;

import bowling.domian.frame.Score;
import bowling.domian.frame.exception.InvalidScoreCalculateException;
import bowling.domian.state.State;

public abstract class Running implements State {
    private static final String GET_SCORE_IMPOSSIBLE_ERROR_MESSAGE = "아직 프레임이 끝나지 않아 계산이 불가능합니다!";

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean canGetScore() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new InvalidScoreCalculateException(GET_SCORE_IMPOSSIBLE_ERROR_MESSAGE);
    }
}
