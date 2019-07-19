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
 * create date  : 2019-07-19 12:01
 */
public class InitState implements State {
    private static final String DISPLAY_STATE = "| ";
    private Point firstPoint;
    private Point secondPoint;

    public InitState(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public static State of() {
        return new InitState(Point.of(0), Point.of(0));
    }

    @Override
    public State update(Point fallCount) {
        return null;
    }

    @Override
    public boolean isOver() {
        return Boolean.FALSE;
    }

    @Override
    public String printState() {
        return DISPLAY_STATE;
    }
}
