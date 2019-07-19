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
 * create date  : 2019-07-19 12:01
 */
public class InitState implements State {
    private static final String DISPLAY_STATE = "| ";

    public static State of() {
        return new InitState();
    }

    @Override
    public State update(Point fallCount) {
        if (fallCount.isStrike()) {
            return new Strike(fallCount);
        }
        if (fallCount.isGutter()) {
            return new Gutter(fallCount);
        }
        return new Hit(fallCount);
    }

    @Override
    public boolean isOver() {
        return Boolean.FALSE;
    }

    @Override
    public String printState() {
        return DISPLAY_STATE;
    }

    @Override
    public Point getFirstBowl() {
        throw new IllegalIndexOfExcpetion();
    }

    @Override
    public Point getSecondBowl() {
        throw new IllegalIndexOfExcpetion();
    }
}
