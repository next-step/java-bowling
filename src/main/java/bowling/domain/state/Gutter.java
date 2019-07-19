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
 * create date  : 2019-07-19 12:32
 */
public class Gutter implements State {
    private static final String DISPLAY_STATE = "-";
    private Point firstBowl;

    public Gutter(Point firstBowl) {
        this.firstBowl = firstBowl;
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
