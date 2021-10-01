package bowling.domain.state.finish;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.exception.state.StrikeStatePinException;

public class Strike implements State {

    public Strike(Pin first) {
        checkPinStrike(first);
    }

    public static void checkPinStrike(Pin pin) {
        if (!pin.isStrike()) {
            throw new StrikeStatePinException();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Score createScore() {
        return null;
    }

    @Override
    public State bowl(Pin pin) {
        return null;
    }

}
