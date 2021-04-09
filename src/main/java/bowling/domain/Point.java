package bowling.domain;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

public class Point {
    private final int point;
    private final boolean didClear;

    public Point(int point) {
        this(point, 0);
    }

    public Point(int curPoint, int prePoint) {
        this.point = verifiedPoint(curPoint);
        this.didClear = verifiedDidClear(curPoint, prePoint);
    }

    private int verifiedPoint(int curPoint) {
        if (!valid(curPoint)) {
            throw new CustomException(ErrorCode.INVALID_POINT);
        }
        return curPoint;
    }

    private boolean verifiedDidClear(int curPoint, int prePoint) {
        if (!valid(curPoint, prePoint)) {
            throw new CustomException(ErrorCode.INVALID_POINT_SUM);
        }
        return curPoint + prePoint == 10;
    }

    private boolean valid(int point) {
        return point >= 0 && point <= 10;
    }

    private boolean valid(int curPoint, int prePoint) {
        return curPoint + prePoint <= 10;
    }

    public int point(){
        return this.point;
    }

    public boolean didClear(){
        return this.didClear;
    }
}
