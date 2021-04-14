package bowling.domain;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

public class Point {

    private static final int NULL = 0;
    private static final boolean PLAYED = true;
    private static final boolean NOT_PLAYED = false;
    private static final int CLEARED = 10;

    private int point;
    private boolean played;

    public Point() {
        this(NULL, NOT_PLAYED);
    }

    private Point(int point, boolean played) {
        this.point = verifiedPoint(point);
        this.played = played;
    }

    public void throwBall(int point) {
        this.point = verifiedPoint(point);
        this.played = PLAYED;
    }

    public void throwBall(Point prePoint, int curPoint) {
        this.point = verifiedPoint(prePoint, curPoint);
        this.played = PLAYED;
    }

    private int verifiedPoint(int point) {
        if (!valid(point)) {
            throw new CustomException(ErrorCode.INVALID_POINT);
        }
        return point;
    }

    private int verifiedPoint(Point prePoint, int curPoint) {
        if (!valid(curPoint)) {
            throw new CustomException(ErrorCode.INVALID_POINT);
        }
        if (!valid(prePoint, curPoint)) {
            throw new CustomException(ErrorCode.INVALID_POINT_SUM);
        }
        return curPoint;
    }

    private boolean valid(int point) {
        return point >= 0 && point <= 10;
    }

    private boolean valid(Point prePoint, int curPoint) {
        return curPoint + prePoint.point <= 10;
    }

    public int point() {
        return this.point;
    }

    public boolean striked() {
        return this.point == CLEARED;
    }

    public boolean spared(Point point) {
        return this.point + point.point == CLEARED;
    }

    public boolean played() {
        return this.played;
    }

}
