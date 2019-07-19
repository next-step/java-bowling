package bowling.domain;

import bowling.domain.state.InitState;
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
    private State defaultState;
    private State bonusState;

    public FinalFrame() {
        this.defaultState = InitState.of();
    }

    @Override
    Frame bowl(int fallCOunt) {
        return null;
    }

    @Override
    boolean isGameOver() {
        return false;
    }

    @Override
    State getState() {
        return null;
    }
}
