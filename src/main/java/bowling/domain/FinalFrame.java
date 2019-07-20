package bowling.domain;

import bowling.domain.state.FinalState;
import bowling.domain.state.InitState;
import bowling.domain.state.State;
import bowling.exception.OutOfBowlCountException;

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
    private State state;

    public FinalFrame() {
        this.state = InitState.of();
    }

    @Override
    Frame bowl(int fallCount) {
        if (isGameOver()) {
            throw new OutOfBowlCountException();
        }
        state = state.update(Point.of(fallCount), true);
        return this;
    }

    @Override
    boolean isGameOver() {
        return state.isOver(true);
    }

    @Override
    State getState() {
        return state;
    }
}
