package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;
import bowling.exception.IllegalBowlCountException;
import bowling.exception.IllegalIndexOfExcpetion;

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
public class Strike implements State {
    public static final String DISPLAY_STATE = "X";
    public static final int SCORE = 10;
    public static final int SCORE_BOWL_COUNT = 2;

    private Point firstBowl;

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
    public boolean isOver(boolean isFinalFrame) {
        if (isFinalFrame) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public String printState() {
        return DISPLAY_STATE;
    }

    @Override
    public Point getFirstBowl() {
        return firstBowl;
    }

    @Override
    public Point getSecondBowl() {
        throw new IllegalIndexOfExcpetion();
    }

    @Override
    public Score stateScore() {
        return Score.ofStrike();
    }

    @Override
    public Score updateScore(Score sourceScore) {
        if (sourceScore.remainCalculate()) {
            return sourceScore.calculate(firstBowl.fallCount());
        }
        return sourceScore;
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
