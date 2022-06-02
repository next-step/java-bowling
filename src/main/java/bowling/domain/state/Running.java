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
        throw new BowlingGameException("Running 중에는 스코어를 불러올 수 없습니다.");
    }

}
