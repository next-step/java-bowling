package bowling.domain.engine.frame.state;

import bowling.domain.RollResult;

public abstract class Finished implements State {

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State transit(RollResult rollResult) {
        throw new IllegalStateException("더 이상 상태를 변경할 수 없습니다.");
    }
}
