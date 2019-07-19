package bowling.domain;

import bowling.domain.state.InitState;
import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.List;

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
    public static final int BONUS_STATE_COUNT = 2;
    private State currentState;
    private List<State> state;

    public FinalFrame() {
        this.currentState = InitState.of();
        this.state = new ArrayList<>();
    }

    @Override
    Frame bowl(int fallCount) {
        currentState = currentState.update(Point.of(fallCount));
        if (currentState.isOver()) {
            state.add(currentState);
        }

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
