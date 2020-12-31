package step2.domain.state;

import step2.domain.Score;

public abstract class Running implements State{

    public boolean isFinish() {
        return false;
    }

    public Score getScore() {
        throw new IllegalArgumentException("아직 점수를 계산할 수 없습니다.");
    }

}
