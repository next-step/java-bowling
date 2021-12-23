package bowling.domain.state.progress;

import bowling.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.state.State;
import bowling.domain.state.StateFactory;

public class GeneralProgress extends Progress {

    private final Progress beforeProgress;

    public GeneralProgress(Frame frame, Pin hitPin, Progress beforeProgress) {
        super(frame, hitPin);

        valid(frame, beforeProgress);
        this.beforeProgress = beforeProgress;
    }

    public boolean isSpare() {
        return hitPin.isSpare(beforeProgress.getHitPin());
    }

    public int getBeforeProgressPinCount() {
        return beforeProgress.getHitCount();
    }

    private void valid(Frame frame, Progress beforeProgress) {
        if (!frame.hasBonusRound()) {
            this.hitPin.sum(beforeProgress.getHitPin());
        }
    }

    @Override
    public State getUpdateState(Frame frame, Pin pin) {
        if (isSpare()) {
            return stateByFrame(frame, pin);
        }

        return StateFactory.normal(this);
    }

    private State stateByFrame(Frame frame, Pin pin) {
        if (frame.hasBonusRound()) {
            return StateFactory.progress(frame, pin, this);
        }

        return StateFactory.spare(this);
    }
}
