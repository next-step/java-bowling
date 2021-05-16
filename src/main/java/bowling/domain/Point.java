package bowling.domain;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

public class Point {

    private static final int MINIMUM_SINGLE_POINT = 0;
    private static final int MAXIMUM_SINGLE_POINT = 10;

    private int point;

    public Point(int point) {
        if (!valid(point)) {
            throw new CustomException(ErrorCode.INVALID_POINT);
        }
        this.point = point;
    }

    private boolean valid(int point) {
        return point >= MINIMUM_SINGLE_POINT && point <= MAXIMUM_SINGLE_POINT;
    }
}
