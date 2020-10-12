package bowling.domain.state;

import bowling.domain.Score;

public abstract class Running implements State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new IllegalStateException("프레임이 종료된 후에 점수를 생성 할 수 있습니다.");
    }
}
