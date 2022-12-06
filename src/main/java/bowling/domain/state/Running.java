package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.CannotCalculateException;

public abstract class Running implements State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new CannotCalculateException("점수계산은 종료 상태만 가능합니다.");
    }
}
