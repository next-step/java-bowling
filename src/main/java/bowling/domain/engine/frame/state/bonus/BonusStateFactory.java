package bowling.domain.engine.frame.state.bonus;

import bowling.domain.concrete.frame.state.bonus.BonusReady;
import bowling.domain.engine.frame.state.State;

public class BonusStateFactory {

    private BonusStateFactory() {

    }

    public static BonusState ready(State beforeState, int leftCount) {
        return new BonusReady(beforeState, leftCount);
    }
}
