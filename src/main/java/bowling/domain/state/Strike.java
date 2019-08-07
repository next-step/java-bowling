package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;
import bowling.exception.IllegalBowlCountException;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 12:49
 */
public class Strike extends SingleBaseState {
    public static final String DISPLAY_STATE = "X";
    public static final int SCORE = 10;
    public static final int SCORE_BOWL_COUNT = 2;

    public Strike(Point fallCount) {
        this.firstBowl = fallCount;
    }

    @Override
    public State update(Point fallCount, boolean isFinalFrame) {
        if (isFinalFrame) {
            return nextState(fallCount, isFinalFrame);
        }
        throw new IllegalBowlCountException();
    }

    @Override
    public String printState() {
        return DISPLAY_STATE;
    }

    @Override
    public Score stateScore() {
        return Score.ofStrike();
    }

    @Override
    public boolean isOver(boolean isFinalFrame) {
        if (isFinalFrame) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private State nextState(Point fallCount, boolean isFinalFrame) {
        if (fallCount.isStrike()) {
            return new DoubleStrike(this, fallCount);
        }
        State lastState = InitState.of().update(fallCount, isFinalFrame);
        return new FinalState(this, lastState);
    }

    @Override
    public String toString() {
        return "Strike{" +
                "firstBowl=" + firstBowl +
                '}';
    }
}
