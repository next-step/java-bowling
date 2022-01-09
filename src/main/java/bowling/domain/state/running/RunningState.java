package bowling.domain.state.running;

import bowling.domain.frame.Score;
import bowling.domain.state.ThrowingState;
import bowling.exception.CannotScoreCalculateException;

public abstract class RunningState implements ThrowingState {

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new CannotScoreCalculateException("아직 기회가 남아있어 점수를 확인할 수 없습니다.");
    }
}
