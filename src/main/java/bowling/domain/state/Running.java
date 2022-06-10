package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.BowlingGameException;

public abstract class Running implements State {
    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new BowlingGameException("러닝 상태에선 스코어를 구할 수 없습니다.");
    }

}
