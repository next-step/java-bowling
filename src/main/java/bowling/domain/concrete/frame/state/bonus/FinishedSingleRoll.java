package bowling.domain.concrete.frame.state.bonus;

import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.frame.state.bonus.BonusFinished;
import bowling.domain.engine.roll.RollResult;

public class FinishedSingleRoll extends BonusFinished {

    private final RollResult rollResult;

    public FinishedSingleRoll(State beforeState,
                              RollResult rollResult) {
        super(beforeState);
        this.rollResult = rollResult;
    }

    @Override
    public String export() {
        return super.export() + "|" + rollResult.export();
    }
}
