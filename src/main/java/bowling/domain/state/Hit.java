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
 * create date  : 2019-07-19 12:22
 */
public class Hit extends SingleBaseState {
    public static final String OUT_OF_POINT_RANGE_EXCEPTION_MESSAGE = "두번째 투구까지의 합이 10점을 넘었습니다.[%d, %d]";

    public Hit(Point firstBowl) {
        this.firstBowl = firstBowl;
    }

    @Override
    public State update(Point fallCount, boolean isFinalFrame) {
        if (firstBowl.checkRemainPointOver(fallCount)) {
            throw new IllegalArgumentException(String.format(OUT_OF_POINT_RANGE_EXCEPTION_MESSAGE, firstBowl.fallCount(), fallCount.fallCount()));
        }
        if (firstBowl.isSpare(fallCount)) {
            return new Spare(this, fallCount);
        }
        return new Miss(this, fallCount);
    }

    @Override
    public String printState() {
        return String.valueOf(firstBowl.fallCount());
    }

    @Override
    public String toString() {
        return "Hit{" +
                "firstBowl=" + firstBowl +
                '}';
    }
}
