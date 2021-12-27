package bowling.domain;

import bowling.annotations.ForUI;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

public abstract class AbstractFrame implements Frame {
    protected final FrameRoundNumber roundNumber;
    protected State state;

    public AbstractFrame(FrameRoundNumber roundNumber) {
        this.roundNumber = roundNumber;
        this.state = new Ready();
    }

    @Override
    public void bowl(int knockedOutCount) {
        state = state.bowl(knockedOutCount);
    }

    @ForUI
    @Override
    public int getFrameNumber() {
        return roundNumber.getRoundNumber();
    }
}
