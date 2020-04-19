package bowling.domain.point;

import bowling.domain.frame.OverThrowBallException;

import java.util.ArrayList;
import java.util.List;

public class Points {
    private static final int FIRST_POINT_INDEX = 0;
    private static final int SECOND_POINT_INDEX = 1;
    private static final int THIRD_POINT_INDEX = 2;

    private static final int FIRST_TRY_COUNT = 1;
    private static final int SECOND_TRY_COUNT = 2;

    private static final int DOUBLE_STRIKE_POINT = 20;
    private static final int SPARE_POINT = 10;

    private List<Point> points;

    public Points() {
        this.points = new ArrayList<>();
    }

    public Points(List<Point> points) {
        this.points = points;
    }

    public Points addPoint(Point point) {
        points.add(point);
        return new Points(points);
    }

    public boolean isTryCount(int count) {
        return points.size() == count;
    }

    public boolean isTryOver(int maxTryableCount) {
        return points.size() >= maxTryableCount;
    }

    public int getSum() {
        return points.stream()
                .mapToInt(Point::getPoint)
                .sum();
    }

    public boolean isFirstStrike() {
        if (isTryCount(FIRST_TRY_COUNT) && getFirstPoint().isMaxPoint()) {
            return true;
        }
        return false;
    }

    public boolean isDoubleStrike() {
        if (isTryCount(SECOND_TRY_COUNT) && getSum() == DOUBLE_STRIKE_POINT) {
            return true;
        }
        return false;
    }

    public boolean isSpare() {
        if (isTryCount(SECOND_TRY_COUNT) && getSum() == SPARE_POINT) {
            return true;
        }
        return false;
    }

    public void validateLeftPoint(Point point) {
        int leftPint = getLeftPoint();
        if (point.getPoint() > leftPint) {
            throw new OverThrowBallException("다시 입력해주세요(남은 핀: " + leftPint + ")");
        }
    }

    private int getLeftPoint() {
        if (isTryCount(FIRST_TRY_COUNT) && !isFirstStrike()) {
            return Point.MAX_POINT - getSum();
        }
        return Point.MAX_POINT;
    }

    public Point getFirstPoint() {
        return points.get(FIRST_POINT_INDEX);
    }

    public Point getSecondPoint() {
        return points.get(SECOND_POINT_INDEX);
    }

    public Point getThirdPoint() {
        return points.get(THIRD_POINT_INDEX);
    }
}
