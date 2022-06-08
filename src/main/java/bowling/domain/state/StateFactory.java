package bowling.domain.state;

import bowling.domain.frame.Frame;
import bowling.exception.NotSupportInstanceException;

public class StateFactory {

    private StateFactory() {
        throw new NotSupportInstanceException();
    }

    public static State initialState() {
        return new Ready();
    }

    public static State nextState(Frame frame) {
        if (frame.hasLastBonusChance()) {
            return new BonusReady();
        }
        return new Ready();
    }
}
