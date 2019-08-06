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
 * create date  : 2019-07-19 13:01
 */
public class Spare extends DoubleBaseState {
    public static final String DELIMITER = "|";
    public static final String DISPLAT_STATE = "/";
    public static final int SCORE = 10;
    public static final int SCORE_BOWL_COUNT = 1;

    public Spare(State state, Point fallCount) {
        this.firstBowl = state;
        this.secondBowl = fallCount;
    }

    @Override
    public State update(Point fallCount, boolean isFinalFrame) {
        if (isFinalFrame) {
            State lastState = InitState.of().update(fallCount, isFinalFrame);
            return new FinalState(this, lastState);
        }
        throw new IllegalBowlCountException();
    }

    @Override
    public String printState() {
        return firstBowl.printState() + DELIMITER + DISPLAT_STATE;
    }

    @Override
    public boolean isOver(boolean isFinalFrame) {
        if (isFinalFrame) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Score stateScore() {
        return Score.ofSpare();
    }

    @Override
    public String toString() {
        return "Spare{" +
                "firstBowl=" + firstBowl +
                ", secondBowl=" + secondBowl +
                '}';
    }
}
