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
 * create date  : 2019-07-19 12:49
 */
public class Strike implements State {
    private static final String DISPLAY_STATE = "X";
    private Point firstBowl;

    public Strike(Point fallCount) {
        this.firstBowl = fallCount;
    }

    @Override
    public State update(Point fallCount) {
        return null;
    }

    @Override
    public boolean isOver() {
        return Boolean.TRUE;
    }

    @Override
    public String printState() {
        return DISPLAY_STATE;
    }
}
