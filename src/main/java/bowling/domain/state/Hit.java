package bowling.domain.state;

import bowling.domain.Point;
import bowling.exception.IllegalIndexOfExcpetion;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 12:22
 */
public class Hit implements State {
    private Point firstBowl;

    public Hit(Point firstBowl) {
        this.firstBowl = firstBowl;
    }

    @Override
    public State update(Point fallCount) {
        // TODO fallCount 임계관련 예외처리 필요
        if (firstBowl.isSpare(fallCount)) {
            return new Spare(this, fallCount);
        }
        return new Miss(this, fallCount);
    }

    @Override
    public boolean isOver() {
        return Boolean.FALSE;
    }

    @Override
    public String printState() {
        return String.valueOf(firstBowl.fallCount());
    }

    @Override
    public Point getFirstBowl() {
        return firstBowl;
    }

    @Override
    public Point getSecondBowl() {
        throw new IllegalIndexOfExcpetion();
    }
}
