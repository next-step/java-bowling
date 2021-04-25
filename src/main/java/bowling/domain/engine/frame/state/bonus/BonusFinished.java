package bowling.domain.engine.frame.state.bonus;

import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.roll.RollResult;

public abstract class BonusFinished implements BonusState {

    private final State beforeState;

    protected BonusFinished(State beforeState) {
        this.beforeState = beforeState;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public BonusState transit(RollResult rollResult) {
        throw new IllegalStateException("더 이상 상태를 변경할 수 없습니다.");
    }

    @Override
    public String export() {
        return beforeState.export();
    }
}
