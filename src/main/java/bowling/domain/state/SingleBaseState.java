package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;
import bowling.exception.IllegalIndexOfExcpetion;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-08-06 17:42
 */
public abstract class SingleBaseState implements State {

    protected Point firstBowl;

    public abstract State update(Point fallCount, boolean isFinalFrame);

    public abstract String printState();

    @Override
    public Score stateScore() {
        return Score.of(firstBowl.fallCount());
    }

    @Override
    public boolean isOver(boolean isFinalFrame) {
        return Boolean.FALSE;
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
    public Score updateScore(Score sourceScore) {
        if (sourceScore.remainCalculate()) {
            return sourceScore.calculate(getFirstBowl().fallCount());
        }
        return sourceScore;
    }
}