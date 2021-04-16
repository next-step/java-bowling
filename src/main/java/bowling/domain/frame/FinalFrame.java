package bowling.domain.frame;

import bowling.domain.Playable;
import bowling.domain.Point;
import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

public class FinalFrame implements Playable {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    private boolean hasExtra;
    private Point[] points;

    public FinalFrame() {
        this.hasExtra = false;
        this.points = points();
    }

    private Point[] points() {
        return new Point[]{new Point(), new Point(), new Point()};
    }

    @Override
    public boolean ended() {
        if (hasExtra) {
            return points[THIRD].played();
        }
        return points[SECOND].played();
    }

    @Override
    public void throwBall(int point) {
        if (hasExtra) {
            throwExtraBall(point);
            return;
        }
        throwNormalBall(point);
        giveExtraChancesIfNeeded();
    }

    private void throwExtraBall(int point) {
        if (!points[SECOND].played()) {
            points[SECOND].throwBall(point);
            return;
        }
        points[THIRD].throwBall(point);
    }

    private void throwNormalBall(int point) {
        if (!points[FIRST].played()) {
            points[FIRST].throwBall(point);
            return;
        }
        if (!valid(points[FIRST], point)) {
            throw new CustomException(ErrorCode.INVALID_POINT_SUM);
        }
        points[SECOND].throwBall(point);
    }

    private boolean valid(Point prePoint, int curPoint) {
        Point point = new Point();
        point.throwBall(curPoint);
        return !prePoint.overs(point);
    }

    private void giveExtraChancesIfNeeded() {
        if (points[FIRST].striked()) {
            hasExtra = true;
            return;
        }
        if (points[SECOND].spared(points[FIRST])) {
            hasExtra = true;
        }
    }

    @Override
    public boolean striked() {
        return points[FIRST].striked();
    }

    @Override
    public boolean spared() {
        return points[FIRST].spared(points[SECOND]);
    }

    public boolean hasExtra() {
        return hasExtra;
    }

    public Point[] getPoints() {
        return points;
    }
}
