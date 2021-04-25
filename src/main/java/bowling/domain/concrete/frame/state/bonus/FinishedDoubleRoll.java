package bowling.domain.concrete.frame.state.bonus;

import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.frame.state.bonus.BonusFinished;
import bowling.domain.engine.roll.RollResult;

public class FinishedDoubleRoll extends BonusFinished {

    private final RollResult firstRollResult;
    private final RollResult secondRollResult;

    public FinishedDoubleRoll(State beforeState,
                              RollResult firstRollResult, RollResult secondRollResult) {
        super(beforeState);
        this.firstRollResult = firstRollResult;
        this.secondRollResult = secondRollResult;
    }

    @Override
    public String export() {
        return String.join("|", super.export(), firstRollResult.export(), secondRollResult.export());
    }

}
