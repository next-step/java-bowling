package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;

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
public class DoubleStrike implements State {
    public static final String DISPLAY_STATE = "X|X";
    private State firstBowl;
    private Point secondBowl;

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
        return firstBowl.getFirstBowl();
    }

    @Override
    public Point getSecondBowl() {
        return secondBowl;
    }

    @Override
    public Score stateScore() {
        return Score.of(firstBowl.stateScore().getScore() + secondBowl.fallCount());
    }

    @Override
    public Score updateScore(Score sourceScore) {
        if (sourceScore.isTwoRemainCount()) {
            return Score.of(sourceScore.getScore() + stateScore().getScore());
        }
        if (sourceScore.remainCalculate()) {
            return sourceScore.calculate(getFirstBowl().fallCount());
        }
        return sourceScore;
    }

    @Override
    public String toString() {
        return "DoubleStrike{" +
                "firstBowl=" + firstBowl +
                ", secondBowl=" + secondBowl +
                '}';
    }
}
