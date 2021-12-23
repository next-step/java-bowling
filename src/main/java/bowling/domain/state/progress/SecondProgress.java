package bowling.domain.state.progress;

import bowling.Pin;

public class SecondProgress extends Progress {

    private final FirstProgress beforeProgress;

    public SecondProgress(Pin hitPin, FirstProgress beforeProgress) {
        super(hitPin);
        this.beforeProgress = beforeProgress;
    }

    public boolean isSpare() {
        return hitPin.isSpare(beforeProgress.getHitPin());
    }

    public int getBeforeProgressPinCount() {
        return beforeProgress.getHitCount();
    }


}
