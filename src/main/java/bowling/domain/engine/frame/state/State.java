package bowling.domain.engine.frame.state;

import bowling.domain.engine.frame.state.bonus.BonusState;
import bowling.domain.engine.roll.RollResult;

public interface State {

    boolean isFinished();

    State transit(RollResult rollResult);

    String export();

    default boolean canPromoteToBonusState() {
        return false;
    }

    default BonusState continueInBonus() {
        throw new IllegalStateException("추가로 던질 수 없는 상태입니다.");
    }

}
