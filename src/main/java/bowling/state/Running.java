package bowling.state;

import bowling.Score;

public abstract class Running implements State {

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Score score() {
        throw new IllegalStateException("게임이 끝나야 점수 계산이 가능합니다.");
    }
}
