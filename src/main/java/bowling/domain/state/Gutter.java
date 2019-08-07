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
public class Gutter extends SingleBaseState {
    public static final String DISPLAY_STATE = "-";

    public Gutter(Point firstBowl) {
        this.firstBowl = firstBowl;
    }

    @Override
    public State update(Point fallCount, boolean isFinalFrame) {
        if (firstBowl.isSpare(fallCount)) {
            return new Spare(this, fallCount);
        }
        if (fallCount.isGutter()) {
            return new DoubleGutter(this, fallCount);
        }
        return new Miss(this, fallCount);
    }

    @Override
    public String printState() {
        return DISPLAY_STATE;
    }

    @Override
    public String toString() {
        return "Gutter{" +
                "firstBowl=" + firstBowl +
                '}';
    }
}
