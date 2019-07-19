package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.state.exception.IllegalBowlCountException;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 13:09
 */
public class Miss implements State {
    public static final String DELIMITER = "|";
    private final State firstBowl;
    private final Point secondBowl;

    public Miss(State state, Point fallCount) {
        this.firstBowl = state;
        this.secondBowl = fallCount;
    }

    @Override
    public State update(Point fallCount) {
        throw new IllegalBowlCountException();
    }

    @Override
    public boolean isOver() {
        return Boolean.TRUE;
    }

    @Override
    public String printState() {
        return firstBowl.printState() + DELIMITER + secondBowl.fallCount() + " " + DELIMITER;
    }

    @Override
    public Point getFirstBowl() {
        return firstBowl.getFirstBowl();
    }

    @Override
    public Point getSecondBowl() {
        return secondBowl;
    }
}
