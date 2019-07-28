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
 * create date  : 2019-07-19 17:01
 */
public class FinalState implements State {
    public static final String DELIMITER = "|";
    private final State firstState;
    private State lastState;

    public FinalState(State first, State last) {
        this.firstState = first;
        this.lastState = last;
    }

    @Override
    public State update(Point fallCount, boolean isFinalFrame) {
        if (!isOver(isFinalFrame)) {
            lastState = lastState.update(fallCount, false);
            return this;
        }
        throw new IllegalBowlCountException();
    }

    @Override
    public boolean isOver(boolean isFinalFrame) {
        if (isFirstState(firstState) || isLastState(lastState)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public String printState() {
        return firstState.printState() + DELIMITER + lastState.printState();
    }

    @Override
    public Point getFirstBowl() {
        return Point.of(firstState.getFirstBowl().fallCount() + firstState.getSecondBowl().fallCount());
    }

    @Override
    public Point getSecondBowl() {
        return Point.of(lastState.getFirstBowl().fallCount());
    }

    @Override
    public Score stateScore() {
        return Score.of(firstState.stateScore().getScore() + lastState.stateScore().getScore());
    }

    @Override
    public Score updateScore(Score sourceScore) {
        // TODO Exception 처리필요
        return null;
    }

    private boolean isFirstState(State checkState) {
        if (checkState instanceof DoubleStrike
                || checkState instanceof Spare) {
            return true;
        }
        return false;
    }

    private boolean isLastState(State checkState) {
        if (checkState instanceof Miss
                || checkState instanceof DoubleGutter
                || checkState instanceof Spare) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "FinalState{" +
                "firstState=" + firstState +
                ", lastState=" + lastState +
                '}';
    }
}
