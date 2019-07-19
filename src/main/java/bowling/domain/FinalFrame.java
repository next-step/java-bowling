package bowling.domain;

import bowling.domain.state.FinalState;
import bowling.domain.state.State;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 15:29
 */
public class FinalFrame extends Frame {
    private FinalState state;

    public FinalFrame() {
        this.state = new FinalState();
    }

    @Override
    Frame bowl(int fallCount) {
        state.update(Point.of(fallCount), true);
        return this;
    }

    @Override
    boolean isGameOver() {
        return state.isOver();
    }

    @Override
    State getState() {
        return (State) state;
    }
}
