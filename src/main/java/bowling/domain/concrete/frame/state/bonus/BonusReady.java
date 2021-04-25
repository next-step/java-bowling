package bowling.domain.concrete.frame.state.bonus;

import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.frame.state.bonus.BonusState;
import bowling.domain.engine.roll.RollResult;

public class BonusReady implements BonusState {

    private static final int MIN = 1;
    private static final int MAX = 2;

    private final State beforeState;
    private final int leftCount;

    public BonusReady(State beforeState, int leftCount) {
        validate(leftCount);
        this.beforeState = beforeState;
        this.leftCount = leftCount;
    }

    private void validate(int leftCount) {
        if (leftCount < MIN || MAX < leftCount) {
            throw new IllegalArgumentException("추가 투구 횟수는 상태에 따라 한 번에서 두 번까지만 허용됩니다.");
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public BonusState transit(RollResult rollResult) {
        if (leftCount == MIN) {
            return new FinishedSingleRoll(beforeState, rollResult);
        }
        return new BonusPlaying(beforeState, rollResult);
    }

    @Override
    public String export() {
        return "";
    }
}
