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
 * create date  : 2019-08-06 17:24
 */
public abstract class DoubleBaseState implements State {

    protected State firstBowl;
    protected Point secondBowl;

    public abstract State update(Point fallCount, boolean isFinalFrame);

    public abstract String printState();

    @Override
    public boolean isOver(boolean isFinalFrame) {
        return Boolean.TRUE;
    }

    @Override
    public Score stateScore() {
        return Score.of(firstBowl.stateScore().getScore() + secondBowl.fallCount());
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
    public Score updateScore(Score sourceScore) {
        if (sourceScore.isTwoRemainCount()) {
            return Score.of(sourceScore.getScore() + stateScore().getScore());
        }
        if (sourceScore.remainCalculate()) {
            return sourceScore.calculate(getFirstBowl().fallCount());
        }
        return sourceScore;
    }
}