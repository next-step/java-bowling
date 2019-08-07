package bowling.domain.state;

import bowling.domain.Point;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 01:10
 */
public class DoubleStrike extends DoubleBaseState {
    public static final String DISPLAY_STATE = "X|X";
    public static final int DOUBLE_STRIKE_SCORE = 20;

    public DoubleStrike(State state, Point fallCount) {
        this.firstBowl = state;
        this.secondBowl = fallCount;
    }

    @Override
    public State update(Point fallCount, boolean isFinalFrame) {
        State lastState = InitState.of().update(fallCount, isFinalFrame);
        return new FinalState(this, lastState);
    }

    @Override
    public String printState() {
        return DISPLAY_STATE;
    }

    @Override
    public boolean isOver(boolean isFinalFrame) {
        if (isFinalFrame) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public String toString() {
        return "DoubleStrike{" +
                "firstBowl=" + firstBowl +
                ", secondBowl=" + secondBowl +
                '}';
    }
}
