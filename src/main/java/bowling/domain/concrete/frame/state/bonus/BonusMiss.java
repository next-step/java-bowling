package bowling.domain.concrete.frame.state.bonus;

import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.frame.state.bonus.BonusFinished;
import bowling.domain.engine.roll.RollResult;

public class BonusMiss extends BonusFinished {

    private final RollResult firstRollResult;
    private final RollResult secondRollResult;

    public BonusMiss(State beforeState,
                              RollResult firstRollResult, RollResult secondRollResult) {
        super(beforeState);
        validate(firstRollResult, secondRollResult);
        this.firstRollResult = firstRollResult;
        this.secondRollResult = secondRollResult;
    }

    private void validate(RollResult firstRoll, RollResult secondRoll) {
        if(firstRoll.exceedMaximumPins(secondRoll)) {
            throw new IllegalArgumentException("두 번의 투구에서 쓰러트린 핀의 개수가 10 보다 클 수 없습니다.");
        }
    }

    @Override
    public String export() {
        return String.join("|", super.export(), firstRollResult.export(), secondRollResult.export());
    }

}
