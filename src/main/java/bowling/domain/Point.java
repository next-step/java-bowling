package bowling.domain;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-16 23:06
 */
public class Point {
    private static final int MAX_POINT = 10;
    private static final String EXCESS_POINT_EXCEPTION_MESSAGE = "점수는 %d 이하의 자연수만 가능합니다.";
    private final int point;

    private Point(int point) {
        this.point = point;
    }

    public static Point of(int fallCount) {
        if (fallCount > MAX_POINT) {
            throw new IllegalArgumentException(String.format(EXCESS_POINT_EXCEPTION_MESSAGE, MAX_POINT));
        }
        return new Point(fallCount);
    }

    public int fallCount() {
        return point;
    }
}
