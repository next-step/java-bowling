package bowling.domain.state.progress;

import bowling.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.state.State;
import bowling.domain.state.StateFactory;

public class FirstProgress extends Progress {

    public FirstProgress(Frame frame, Pin hitPin) {
        super(frame, hitPin);
    }

    @Override
    public State getUpdateState(Frame frame, Pin pin) {
        if (isStrike()) {
            return StateFactory.strike();
        }

        return StateFactory.progress(frame, pin, this);
    }

    private boolean isStrike() {
        return this.hitPin.isStrike();
    }

}
