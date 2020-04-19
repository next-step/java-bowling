package bowling.domain.State;

import bowling.domain.Score;
import bowling.exception.CannotCalculateException;

abstract class Running implements State {
    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new CannotCalculateException("계산 할 수 있는 상태(Running)가 아닙니다.");
    }
}
