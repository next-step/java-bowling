package bowling.domain.concrete.frame.state.bonus;

import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.frame.state.bonus.BonusState;
import bowling.domain.engine.roll.RollResult;

public class BonusPlaying implements BonusState {

    private final State beforeState;
    private final RollResult rollResult;

    public BonusPlaying(State beforeState, RollResult rollResult) {
        this.beforeState = beforeState;
        this.rollResult = rollResult;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public State transit(RollResult secondRollResult) {
        if (!rollResult.isClear() && rollResult.isClearWith(secondRollResult)) {
            return new BonusSpare(beforeState, rollResult, secondRollResult);
        }

        if (rollResult.isClear()) {
            return new FinishedDoubleRoll(beforeState, rollResult, secondRollResult);
        }

        return new BonusMiss(beforeState, rollResult, secondRollResult);
    }

    @Override
    public String export() {
        return beforeState.export() + "|" + rollResult.export();
    }
}
