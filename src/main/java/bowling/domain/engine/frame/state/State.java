package bowling.domain.engine.frame.state;

import bowling.domain.RollResult;

public interface State {

    boolean isFinished();

    State transit(RollResult rollResult);

    String export();

    default boolean canPromoteToBonusState() {
        return false;
    }

}
