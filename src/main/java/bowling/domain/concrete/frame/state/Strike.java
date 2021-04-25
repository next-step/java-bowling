package bowling.domain.concrete.frame.state;

import bowling.domain.engine.frame.state.Finished;
import bowling.domain.engine.frame.state.bonus.BonusState;
import bowling.domain.engine.frame.state.bonus.BonusStateFactory;
import bowling.domain.engine.roll.RollResult;

public class Strike extends Finished {

    private static final int BONUS_LEFT_COUNT = 2;

    private final RollResult strikeRoll;

    public Strike(RollResult rollResult) {
        validate(rollResult);
        this.strikeRoll = rollResult;
    }

    private void validate(RollResult rollResult) {
        if (!rollResult.isClear()) {
            throw new IllegalArgumentException("스트라이크를 쳤을 때만 Strike 상태를 만들 수 있습니다.");
        }
    }

    @Override
    public boolean canPromoteToBonusState() {
        return true;
    }

    @Override
    public BonusState continueInBonus() {
        return BonusStateFactory.ready(this, BONUS_LEFT_COUNT);
    }

    @Override
    public String export() {
        return strikeRoll.export();
    }
}
