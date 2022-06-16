package bowling.domain.state;

import bowling.exception.NotSupportInstanceException;

public class StateFactory {

    private StateFactory() {
        throw new NotSupportInstanceException();
    }

    public static State initialState() {
        return new Ready();
    }

    public static State nextState(States states) {
        if (states.hasLastBonusChance()) {
            return new BonusReady();
        }
        return new Ready();
    }
}
