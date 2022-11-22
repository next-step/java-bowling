package bowling.domain.state;

import bowling.domain.Score2;

public abstract class Running implements State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score2 getScore() {
        throw new IllegalStateException("점수계산은 종료 상태만 가능합니다.");
    }
}
