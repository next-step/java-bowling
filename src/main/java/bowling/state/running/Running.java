package bowling.state.running;

import bowling.exception.CannotScoreCalculateException;
import bowling.frame.Score;
import bowling.state.Throwing;

public abstract class Running implements Throwing {

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new CannotScoreCalculateException("아직 기회가 남아있어 점수를 확인할 수 없습니다.");
    }
}
